package pl.arek.claiming;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pl.arek.claiming.domain.ResultData;


//klasa będąca repozytorium przetworzonych danych (rezultatów)

@Repository
public class ResultDAO {
	
	public List<ResultData> resData = new ArrayList<>();
	
	//wstawienie danych już po przetworzeniu
	public void addResultData(ResultData rd){
		resData.add(rd);
	}
	
	public List<ResultData> getClaim(){
		System.out.println("Zawartość tabeli:");
		if(resData.isEmpty()){
			System.out.println("Tabela jest pusta");
		}
		else{
			System.out.println("Tabela nie jest pusta");
			for(ResultData rd : resData){
				System.out.println("Jestem w tabeli");
				System.out.println(rd.getWBS());
				System.out.println(rd.getOtype());
				System.out.println(rd.getSat());
				System.out.println(rd.getSun());
				System.out.println(rd.getMon());
				System.out.println(rd.getTue());
				System.out.println(rd.getWen());
				System.out.println(rd.getThu());
				System.out.println(rd.getFri());
			}
		}
		
		return resData;
	}
	
}
