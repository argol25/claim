package pl.arek.claiming.domain.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import pl.arek.claiming.domain.dao.ResultDAO;
import pl.arek.claiming.domain.model.ClaimData;
import pl.arek.claiming.domain.model.ResultData;
//import pl.arek.claiming.domain.dao.ClaimDAO;
import pl.arek.claiming.domain.services.DataProcessing;

@Service
public class ResultDAOsrv {

	@Autowired
	protected ResultDAO rdao;
	@Autowired
	protected ClaimDAOsrv cdaos;
	// DataProcessing dp;

	protected List<ClaimData> claimDataList = new ArrayList<>();
	protected List<ResultData> resultDataList = new ArrayList<>();
	DataProcessing dp;
	
	/*
	 * // pobranie danych z bazy ClaimDAO public List<ClaimData> getBasicData()
	 * { return cdao.getClaiming(); }
	 */

	// przetworzenie i dodanie danych
	public void processData() {
		/*
		 * System.out.println("Rozpoczynam process Data");
		 * 
		 * Iterator<ClaimData> cdi = cdaos.dataProcess().iterator();
		 * 
		 * System.out.println("Przeglądam zawartość tabeli ClaimData");
		 * 
		 * while(cdi.hasNext()){
		 * 
		 * ClaimData cd = (ClaimData) cdi.next(); DataProcessing dp = new
		 * DataProcessing(cd.getStartTime().toString(),
		 * cd.getEndTime().toString(), cd.getStHour(), cd.getEndHour(),
		 * cd.getSb1Day(), cd.getSb2Day(), cd.getAccountName());
		 * 
		 * rdao.save(dp.claimDataCount()); }
		 */
		int count=0;
		
		
		try{
			rdao.deleteAll();
			wait(10000);
		}catch(Exception e){

		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		// dodaję całą zawartość tabeli ClaimData
		claimDataList.addAll(cdaos.showClaimData());
		
		for (ClaimData cd : claimDataList) {

			String cdSDate = sdf.format(cd.getStartTime());
			String cdEDate = sdf.format(cd.getEndTime());
			
			// przekazuje dane z kolejnych wierszy tabeli ClaimData
			dp = new DataProcessing(cdSDate, cdEDate, cd.getStHour(), cd.getEndHour(), cd.getSb1Day(),
					cd.getSb2Day(), cd.getAccountName());
			dp.claimDataCount();
			
			resultDataList.addAll(dp.provideRD());
			for(ResultData rda : resultDataList){
				rdao.save(rda);
			}
		}
		
		
	}

	// zwraca wszystkie dane związane z tym jak claimować
	public List<ResultData> showHowToClaim() {
		return (List<ResultData>) rdao.findAll();
	}

}
