package pl.arek.claiming.domain.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.arek.claiming.domain.dao.ResultDAO;
import pl.arek.claiming.domain.model.ClaimData;
import pl.arek.claiming.domain.model.ResultData;
import pl.arek.claiming.domain.services.DataProcessing;

@Service
public class ResultDAOsrv {

	@Autowired
	protected ResultDAO rdao;
	@Autowired
	protected ClaimDAOsrv cdaos;

	protected List<ClaimData> claimDataList = new ArrayList<>();
	protected List<ResultData> resultDataList = new ArrayList<>();
	DataProcessing dp;
	int count = 0;

	static double no1 = 0;
	static double no2 = 0;
	static double sum = 0;
	//static String s = null;
	//static String sr = null;

	// przetworzenie i dodanie danych
	public void processData() {

		if (count == 0) {
			setCheckedStatus();
		}

		// czyści tabelę rezultatów
		resultDataList.clear();
		// czyści bazę danych rezultatów
		rdao.deleteAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		// dodaję całą zawartość tabeli ClaimData
		claimDataList.addAll(cdaos.showClaimData());

		for (ClaimData cd : claimDataList) {
			count ++;
			if (cd.isAlreadyChecked() != true) {
				String cdSDate = sdf.format(cd.getStartTime());
				String cdEDate = sdf.format(cd.getEndTime());

				// przekazuje dane z kolejnych wierszy tabeli ClaimData
				dp = new DataProcessing(cdSDate, cdEDate, cd.getStHour(), cd.getEndHour(), cd.getSb1Day(),
						cd.getSb2Day(), cd.getAccountName());
				dp.claimDataCount();

				resultDataList.addAll(dp.provideRD());

				// zmienia status obiektu na sprawdzony
				cd.setAlreadyChecked(true);
			}
		}
		cleanAndSaveData();

	}

	// metoda przegląda uzyskane rezultaty i czyści je z niepotrzebnie
	// zduplikowanych wierszy dla danego dnia oraz zapisuje je ostatecznie do
	// bazy danych
	public void cleanAndSaveData() {
		List<ResultData> rdl = new ArrayList<>();

		Iterator<ResultData> rdi = resultDataList.iterator();
		ResultData ird;

		for (ResultData rda : resultDataList) {
			// sprawdza czy dana encja była już sprawdzana i wykorzystana
			if ((rda.isChecked()) != false) {
				while (rdi.hasNext()) {
					// pobiera kolejny po sprawdzanej encji obiekt w celu
					// sprawdzenia czy nie pokrywają się podstawowe dane
					ird = rdi.next();
					if (!rda.equals(ird)) {
						if (rda.getWeekNo() == ird.getWeekNo()) {
							if (rda.getWBSel().equals(ird.getWBSel())) {
								if (rda.getOType().equals(ird.getOType())) {
									// obiekty pokrywają się pod względem
									// podstawowych danych, można je więc
									// scalić, żeby uniknąć tworzenia nowcyh
									// wpisów w finalnej tabeli

									// ustawia element jako niesprawdzony, żeby
									// uniknąć duplikowania danych w finalnej
									// tabeli
									ird.setChecked(false);

									// sprawdza poszczególne dni tygodnia i
									// wstawia wyliczone dane
									rda.setSat(countSum(rda.getSat(), ird.getSat()));
									rda.setSun(countSum(rda.getSun(), ird.getSun()));
									rda.setMon(countSum(rda.getMon(), ird.getMon()));
									rda.setTue(countSum(rda.getTue(), ird.getTue()));
									rda.setWen(countSum(rda.getWen(), ird.getWen()));
									rda.setThu(countSum(rda.getThu(), ird.getThu()));
									rda.setFri(countSum(rda.getFri(), ird.getFri()));

									// dodaje obiekt do tabeli rdl
									rdl.add(rda);

								}
							}
						}
					} else
						rdi.next();
				}
				// jeżeli nie ma możliwości scalenia obiektów dodaje obecnie
				// sprawdzany
				rdl.add(rda);
			}

		}

		// zapisuje w bazie danych wszystkie obiekty zapisane w tabeli rdl
		for (ResultData rda : rdl) {
			rdao.save(rda);
		}

	}

	// sortuje dane malejąca po numerze tygodnia
	private Sort orderBy() {
		return new Sort(Sort.Direction.DESC, "weekNo", "wBSel", "oType");
	}

	// zwraca wszystkie dane związane z tym jak claimować
	public List<ResultData> showHowToClaim() {
		return (List<ResultData>) rdao.findAll(orderBy());
		// return (List<ResultData>) rdao.findAllByOrderByWeekNo();
	}

	// ustawia statusy wszystkich encji w bazie danych Claim Data jako
	// niesprawdzone
	public void setCheckedStatus() {
		for (ClaimData cd : claimDataList) {
			cd.setAlreadyChecked(false);
		}
	}

	// formatuje podany string zamieniając kropkę na przecinek
	public String changeFromDotToComma(String s) {
		return s.replace('.', ',');
	}

	// formatuje podany string zamieniając przecinek na kropkę
	public String changeFromCommaToDot(String s) {
		return s.replace(',', '.');
	}

	// oblicza sumę dla danego dnia jeżeli możliwe jest scalenie danego dnia
	// obiektów
	public String countSum(String s, String sr) {
		no1 = 0;
		no2 = 0;
		sum = 0;

		if (s != null) {
			String srep = changeFromCommaToDot(s);
			no1 = Double.parseDouble(srep);
		}

		if (sr != null) {
			String srep2 = changeFromCommaToDot(sr);
			no2 = Double.parseDouble(srep2);
		}

		sum = no1 + no2;

		if (sum > 0) {
			s = Double.toString(sum);
			s = changeFromDotToComma(s);
		} else
			s = null;

		return s;
	}
}
