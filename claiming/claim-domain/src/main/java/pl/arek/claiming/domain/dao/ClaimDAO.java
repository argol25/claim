package pl.arek.claiming.domain.dao;

//klasa będąca repozytorium danych użytych przy wprowadzaniu i wykorzystaniu danych

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.arek.claiming.domain.model.ClaimData;

@Repository
public interface ClaimDAO extends CrudRepository<ClaimData, Long> {

	public ClaimData findById(Long id);

	// List<ClaimData> clData = new ArrayList<ClaimData>();
	/*
	 * //dodajemy tylko dane od użytkownika public void addBasicData(ClaimData
	 * data){ clData.add(data); }
	 * 
	 * public List<ClaimData> getClaiming(){ return clData; }
	 */

}
