package pl.arek.claiming.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import pl.arek.claiming.domain.services.ResultDAOsrv;

@Controller
public class ResultController {

	@Autowired
	protected ResultDAOsrv rdaos;

	@RequestMapping("/claimresults")
	public String listResultsData(Model model) {
		rdaos.processData();
		model.addAttribute("results", rdaos.showHowToClaim());
		return "claimresults";
	}

}
