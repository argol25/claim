package pl.arek.claiming.domain.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.arek.claiming.domain.dao.ClaimDAO;
import pl.arek.claiming.domain.model.ClaimData;
import pl.arek.claiming.domain.services.ResultDAOsrv;

@Service
public class ClaimDAOsrv {
	
	@Autowired
	protected ClaimDAO cdao;
	
	// metoda odpowiadająca za dodanie rekordu do bazy
	public void saveClaimData(ClaimData cd){
		cdao.save(cd);
	}
	
	// metoda odpowiadająca za wyciągnięcie rekordu z bazy
	public List<ClaimData> showClaimData(){
		return (List<ClaimData>)cdao.findAll();
	}
	
	// wyświetl pojedynczy rekord po Id
	public ClaimData showCDbyId(Long id){
		return cdao.findById(id);
	}
	
	
	
	
	// modyfikacja rekordu

	/*
	 * posortowanie danych zgodnie z tygodniem wykonania aktywności oraz
	 * scalenie aktywności dla tego samego klienta w ten sam dzień przy tym
	 * samym typie nadgodzin
	 */

}
