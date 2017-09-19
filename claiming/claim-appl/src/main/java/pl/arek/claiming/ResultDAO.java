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
	
	//pobierz wszystkie dane
	public List<ResultData> getClaim(){
		return resData;
	}
	
	//wykasuj zawartość listy
	public void clearResultData(){
		resData.clear();
	}
	
	
	
}
