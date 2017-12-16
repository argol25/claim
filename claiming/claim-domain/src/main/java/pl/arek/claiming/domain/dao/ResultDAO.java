package pl.arek.claiming.domain.dao;

//import java.util.List;
//import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.arek.claiming.domain.model.ResultData;

//klasa będąca repozytorium przetworzonych danych (rezultatów)

@Repository
public interface ResultDAO extends CrudRepository<ResultData, Long> {

	public ResultData findById(Long id);
	// dodaj dane
	// skasuj dane
	// zaktualizuj dane??
	// znajdź wpis
	//

	/*
	 * public List<ResultData> resData = new ArrayList<>();
	 * 
	 * //wstawienie danych już po przetworzeniu public void
	 * addResultData(ResultData rd){ resData.add(rd); }
	 */

	// pobierz wszystkie dane public List<ResultData> getClaim(){ return
	// resData; }

	// wykasuj zawartość listy public void clearResultData(){ resData.clear();}

}
