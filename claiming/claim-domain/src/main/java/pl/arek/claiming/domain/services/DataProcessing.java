package pl.arek.claiming.domain.services;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import pl.arek.claiming.domain.dao.ResultDAO;
import pl.arek.claiming.domain.model.ResultData;
//import pl.arek.claiming.ResultDAO;

public class DataProcessing {

	// łączna liczba nadgodzin
	double otAmountInSummary = 0;

	// nazwy dni w formacie Mon, Tue...
	String day1Name;
	String day2Name;

	// różnica pomiędzy podanymi datami;
	int dayDifference = 0;

	// data roczpoczęcia i zakończenia OT yyyy/MM/dd
	String otStartDate;
	String otEndDate;

	// godziny rozpoczęcia OT HH:mm
	String otStartTime;
	String otEndTime;

	// liczba godzin SB
	String sb1Day;
	String sb2Day;

	// sformatowana data startu i końca OT yyyy/
	String fStartDate;
	String fEndDate;

	// formaty dat, które zostaną użyte
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	SimpleDateFormat fDays = new SimpleDateFormat("yyyy/MM/dd");
	DecimalFormat df = new DecimalFormat("#.##");

	// daty startu i zakończenia w formacie Date yyyy/MM/dd HH:mm
	Date dStartDate;
	Date dEndDate;

	// daty startu i zakończenia w formacie DAte yyyy/MM/dd
	Date ddStartDate;
	Date ddEndDate;

	// daty startu i zakończenia w formacie DateTime
	DateTime dtStartDate;
	DateTime dtEndDate;
	DateTime ddtStartDate;
	DateTime ddtEndDate;

	// czasy ses3 i seot
	double ses30 = 0.00f;
	double seot0 = 0.00f;

	// dodatkowe zmienne w przypadku różnicy dnia, żeby policzyć OT dla obu dni
	double ses31 = 0.00f;
	double seot1 = 0.00f;

	// kolekcja z nazwami dni
	final HashMap<Integer, String> weekdayNames = new HashMap<>();

	// kolekcja z kodami
	final HashMap<String, String> accCodes = new HashMap<>();

	// kod danego klienta
	String cCode;

	// kolekcja obiektów ResultData
	List<ResultData> resDataObj = new ArrayList<>();

	@Autowired
	protected ResultDAO rdao;

	double oneMin = 0.017;

	public DataProcessing(String otd, String oed, String ost, String oet, String s1d, String s2d, String aN) {
		otStartDate = otd;
		otEndDate = oed;
		otStartTime = ost;

		// zmniejsza godzinę końcową 00:00, żeby nie spowodować błędów w
		// wyliczeniach
		if (oet.equals("00:00")) {
			otEndTime = "23:59";
			oet = "23:59";
		} else
			otEndTime = oet;

		sb1Day = s1d;
		sb2Day = s2d;

		// uzyskujemy format yyyy/MM/dd HH:mm w formie String
		fStartDate = otd + " " + ost;
		fEndDate = oed + " " + oet;

		// zamieniamy String na Date w odpowiedniej formie
		try {

			// yyyy/MM/dd HH:mm
			dStartDate = format.parse(fStartDate);
			dEndDate = format.parse(fEndDate);

			// yyyy/MM/dd
			ddStartDate = fDays.parse(fStartDate);
			ddEndDate = fDays.parse(fEndDate);

			// DateTime yyyy/MM/dd HH:mm
			dtStartDate = new DateTime(dStartDate);
			dtEndDate = new DateTime(dEndDate);

			// DateTime yyyy/MM/dd
			ddtStartDate = new DateTime(ddStartDate);
			ddtEndDate = new DateTime(ddEndDate);

			// wypełnia kolekcję tak, żeby na podstawie daty wskazać odpowiednio
			// dzień tygodnia
			weekdayNames.put(1, "Mon");
			weekdayNames.put(2, "Tue");
			weekdayNames.put(3, "Wen");
			weekdayNames.put(4, "Thu");
			weekdayNames.put(5, "Fri");
			weekdayNames.put(6, "Sat");
			weekdayNames.put(7, "Sun");

			// wypełnia kolekcję kodami klientów
			accCodes.put("customer#1", "wbbCust1");
			accCodes.put("customer#2", "wbbCust2");
			accCodes.put("customer#3", "wbbCust3");
			accCodes.put("customer#4", "wbbCust4");
			accCodes.put("customer#5", "wbbCust5");

			// wylicza różnicę dni pomiędzy końcem a początkiem OT
			this.dayDifference = Days.daysBetween(ddtStartDate, ddtEndDate).getDays();

			// wstawia kod dla podanego klienta
			cCode = accCodes.get(aN);

			// claimDataCount();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void claimDataCount() {

		// ile jest w sumie minut różnicy pomiędzy podanymi datami
		int minDiff = 0;

		// numer dnia tygodnia, pozwoli na wyznaczenie jego nazwy
		int dayOfWeekNo0 = 0;
		int dayOfWeekNo1 = 0;

		minDiff = Minutes.minutesBetween(dtStartDate, dtEndDate).getMinutes();
		otAmountInSummary = (double) minDiff / 60;

		// jeżeli nadgodziny są wykonywane w tym samym dniu
		if (dayDifference == 0) {

			try {

				// data referencyjna potrzebna do wyliczeń yyyy/MM/dd 00:00
				String sRefDate = otStartDate + " 00:00";
				Date sdRefDate = format.parse(sRefDate);
				DateTime sddRefDate = new DateTime(sdRefDate);

				// różnica minut między północą a czasem startu
				int iMinDiff0 = 0;
				double fMinDiff0 = 0;

				// podaje który to jest dzień tygodnia jako klucz i pobiera
				// nazwę dnia z kolekcji weekdayNames
				dayOfWeekNo0 = dtStartDate.getDayOfWeek();
				day1Name = weekdayNames.get(dayOfWeekNo0);

				iMinDiff0 = Minutes.minutesBetween(sddRefDate, dtStartDate).getMinutes();
				fMinDiff0 = (double) iMinDiff0 / 60;

				if (Float.parseFloat(sb1Day) < fMinDiff0) {
					// jako, że godzina rozpoczęcia OT to czas już poza ramami
					// stanby'a, liczymy tylko SEOT
					seot0 = otAmountInSummary;
				} else if (Float.parseFloat(sb1Day) >= fMinDiff0) {
					// ses3 jest równe różnicy ilości standby'a i godziny
					// rozpoczęcia OT
					ses30 = Float.parseFloat(sb1Day) - fMinDiff0;
					seot0 = otAmountInSummary - ses30;
				}

				// uzupełnia dane dla rezultatów w przypadku kiedy wystąpiły OT
				// ses3
				if (ses30 > 0) {

					ResultData rd = new ResultData();
					rd.setWBSel(cCode);
					rd.setOType("SES3");
					establishAndSetWeekday(ses30, day1Name, rd);
					// ustawia numer dnia tygodnia; jeżeli jest do sobota lub
					// niedziela dodaje 1 (przestawienie na tryb sobota->piątek
					if (day1Name.equals("Sat") || day1Name.equals("Sun"))
						rd.setWeekNo(establisWeekNo(ddtStartDate) + 1);
					else
						rd.setWeekNo(establisWeekNo(ddtStartDate));
					resDataObj.add(rd);

				}

				// uzupełnia dane dla rezultatów w przypadku kiedy wystąpiły OT
				// seot
				if (seot0 > 0) {
					// dodaję jeszcze minutę w przypadku kiedy godzina
					// zakończenia to 00:00
					if (otEndTime.equals("23:59"))
						seot0 = (seot0 + oneMin);

					ResultData rd = new ResultData();
					rd.setWBSel(cCode);
					rd.setOType("SEOT");
					establishAndSetWeekday(seot0, day1Name, rd);
					rd.setWeekNo(establisWeekNo(ddtStartDate));
					resDataObj.add(rd);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// w przypadku gdy różnica dni wynosi 1
		else if (dayDifference > 0) {
			try {
				// data referencyjna potrzebna do wyliczeń yyyy/MM/dd 23:59
				String sRefDate = otStartDate + " 23:59";
				Date sdRefDate = format.parse(sRefDate);
				DateTime sddRefDate = new DateTime(sdRefDate);

				// różnica minut między północą na koniec pierwszego dnia a
				// czasem startu
				int iMinDiff0 = 0;
				double fMinDiff0 = 0;

				// czas referejcyjny symbolizujący ilość godzin w ciągu doby
				final double dayHoursAmount = 24;

				// zmienna pozwalająca wyliczyć ile nadgodzin znajduje się w
				// godzinach standby'a
				double sbHtoMidnight = 0;

				// zmienna referencyjna zawierająca liczbę zaplanowanych godzin
				// stanby'a w postaci double
				double sb1DayRef = Float.parseFloat(sb1Day);
				double sb2DayRef = Float.parseFloat(sb2Day);

				// podaje który to jest dzień tygodnia jako klucz i pobiera
				// nazwę dnia z kolekcji weekdayNames
				dayOfWeekNo0 = dtStartDate.getDayOfWeek();
				day1Name = weekdayNames.get(dayOfWeekNo0);

				iMinDiff0 = Minutes.minutesBetween(dtStartDate, sddRefDate).getMinutes();
				// dodaję brakującą minutę do północy
				iMinDiff0 = iMinDiff0 + 1;
				fMinDiff0 = (double) iMinDiff0 / 60;

				sbHtoMidnight = dayHoursAmount - fMinDiff0;

				// wyliczenia dla pierwszego dnia
				if (sb1DayRef > sbHtoMidnight) {
					ses30 = sb1DayRef - sbHtoMidnight;
					seot0 = fMinDiff0 - ses30;
				} else if (sb1DayRef <= sbHtoMidnight) {
					seot0 = fMinDiff0;
				}

				// ilość nadgodzin drugiego dnia
				double day2OT = 0;

				day2OT = otAmountInSummary - (ses30 + seot0);

				if (sb2DayRef < day2OT) {
					ses31 = sb2DayRef;
					seot1 = day2OT - sb2DayRef;
				} else if (sb2DayRef >= day2OT) {
					ses31 = day2OT;
				}

				// podaje który to jest dzień tygodnia jako klucz i pobiera
				// nazwę dnia z kolekcji weekdayNames
				dayOfWeekNo1 = dtEndDate.getDayOfWeek();
				day2Name = weekdayNames.get(dayOfWeekNo1);

				// uzupełnia dane dla rezultatów w przypadku kiedy wystąpiły
				// OTses3 dla 1 dnia
				if (ses30 > 0) {
					ResultData rd = new ResultData();
					rd.setWBSel(cCode);
					rd.setOType("SES3");
					establishAndSetWeekday(ses30, day1Name, rd);
					
					// ustawia numer dnia tygodnia; jeżeli jest do sobota lub
					// niedziela dodaje 1 (przestawienie na tryb sobota->piątek
					if (day1Name.equals("Sat") || day1Name.equals("Sun"))
						rd.setWeekNo(establisWeekNo(ddtStartDate) + 1);
					else
						rd.setWeekNo(establisWeekNo(ddtStartDate));

					resDataObj.add(rd);

				}

				// uzupełnia dane dla rezultatów w przypadku kiedy wystąpiły OT
				// seot dla 1 dnia
				if (seot0 > 0) {
					// dodaję jeszcze minutę w przypadku kiedy godzina
					// zakończenia to 00:00
					if (otEndTime.equals("23:59"))
						seot0 = (seot0 + oneMin);

					ResultData rd = new ResultData();
					rd.setWBSel(cCode);
					rd.setOType("SEOT");
					establishAndSetWeekday(seot0, day1Name, rd);
					// ustawia numer dnia tygodnia; jeżeli jest do sobota lub
					// niedziela dodaje 1 (przestawienie na tryb sobota->piątek
					if (day1Name.equals("Sat") || day1Name.equals("Sun"))
						rd.setWeekNo(establisWeekNo(ddtStartDate) + 1);
					else
						rd.setWeekNo(establisWeekNo(ddtStartDate));
					
					resDataObj.add(rd);

				}

				// uzupełnia dane dla rezultatów w przypadku kiedy wystąpiły OT
				// ses3 dla 2 dnia
				if (ses31 > 0) {
					ResultData rd = new ResultData();
					rd.setWBSel(cCode);
					rd.setOType("SES3");
					establishAndSetWeekday(ses31, day2Name, rd);
					// ustawia numer dnia tygodnia; jeżeli jest do sobota lub
					// niedziela dodaje 1 (przestawienie na tryb sobota->piątek
					if (day1Name.equals("Sat") || day1Name.equals("Sun"))
						rd.setWeekNo(establisWeekNo(ddtStartDate) + 1);
					else
						rd.setWeekNo(establisWeekNo(ddtStartDate));
					resDataObj.add(rd);

				}

				// uzupełnia dane dla rezultatów w przypadku kiedy wystąpiły OT
				// seot dla 2 dnia
				if (seot1 > 0) {
					// dodaję jeszcze minutę w przypadku kiedy godzina
					// zakończenia to 00:00
					if (otEndTime.equals("23:59"))
						seot0 = (seot0 + oneMin);

					ResultData rd = new ResultData();
					rd.setWBSel(cCode);
					rd.setOType("SEOT");
					establishAndSetWeekday(seot1, day2Name, rd);
					// ustawia numer dnia tygodnia; jeżeli jest do sobota lub
					// niedziela dodaje 1 (przestawienie na tryb sobota->piątek
					if (day1Name.equals("Sat") || day1Name.equals("Sun"))
						rd.setWeekNo(establisWeekNo(ddtStartDate) + 1);
					else
						rd.setWeekNo(establisWeekNo(ddtStartDate));
					resDataObj.add(rd);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<ResultData> provideRD() {
		return resDataObj;
	}

	// metoda wstawiające odpowidnią liczbę godzin dla danego dnia wystąpienia
	// OT
	void establishAndSetWeekday(double ot, String dName, ResultData rd) {
		if (dName.equals("Mon"))
			rd.setMon(df.format(ot));
		else if (dName.equals("Tue"))
			rd.setTue(df.format(ot));
		else if (dName.equals("Wen"))
			rd.setWen(df.format(ot));
		else if (dName.equals("Thu"))
			rd.setThu(df.format(ot));
		else if (dName.equals("Fri"))
			rd.setFri(df.format(ot));
		else if (dName.equals("Sat"))
			rd.setSat(df.format(ot));
		else if (dName.equals("Sun"))
			rd.setSun(df.format(ot));
	}

	// metoda określająca numer tygodnia (w roku) danego dnia
	int establisWeekNo(DateTime date) {
		return date.getWeekOfWeekyear();
	}

}