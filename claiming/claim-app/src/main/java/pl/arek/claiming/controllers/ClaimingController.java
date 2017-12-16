package pl.arek.claiming.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.ui.Model;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

//import pl.arek.claiming.domain.dao.ClaimDAO;
//import pl.arek.claiming.domain.dao.ResultDAO;
import pl.arek.claiming.domain.model.ClaimData;
//import pl.arek.claiming.domain.model.ResultData;
import pl.arek.claiming.dto.ClaimDataDTO;
//import pl.arek.claiming.domain.services.DataProcessing;
//import pl.arek.claiming.domain.services.ResultDAOsrv;
import pl.arek.claiming.domain.services.ClaimDAOsrv;

@Controller
public class ClaimingController {

	@Autowired
	protected ClaimDAOsrv cdaos;
	/*
	 * @Autowired ResultDAO rdao;
	 */
	//@Autowired
	//protected ResultDAOsrv daosrv;
	
	@RequestMapping("/listaclaim")
	public String listClaimData(Model model){
		model.addAttribute("claim", cdaos.showClaimData());
		return "listaclaim";
	}
	
	@RequestMapping("/claimdata")
	public String addClaimData(HttpServletRequest request, @ModelAttribute("claimDto") @Valid ClaimDataDTO claimDto,
			BindingResult result) {

		if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
			ClaimData cd = new ClaimData();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			try {
				cd.setStartTime(sdf.parse(claimDto.getStartTime()));
				cd.setEndTime(sdf.parse(claimDto.getEndTime()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			cd.setAccountName(claimDto.getAccountName());
			cd.setStHour(claimDto.getStHour());
			cd.setEndHour(claimDto.getEndHour());
			cd.setSb1Day(claimDto.getSb1Day());
			cd.setSb2Day(claimDto.getSb2Day());
			
			cdaos.saveClaimData(cd);

			// DataProcessing dp = new
			// DataProcessing(cd.getStartTime().toString(),
			// cd.getEndTime().toString(), cd.getStartHour(), cd.getEndHour(),
			// cd.getSb1Day(), cd.getSb2Day(), cd.getAccountName());
			/*DataProcessing dp = new DataProcessing(claimDto.getStartTime(), claimDto.getEndTime(), claimDto.getStHour(),
					claimDto.getEndHour(), claimDto.getSb1Day(), claimDto.getSb2Day(), claimDto.getAccountName());

			for (ResultData rd : dp.claimDataCount()) {
				rdao.addResultData(rd);
			}
			*/
			
			//daosrv.processData();
			
			return "redirect:/claimdata";
		}

		return "claimdata";
	}
	
	/*
	@RequestMapping("/claimresults")
	public String showClaimData(Model model) {
		model.addAttribute("resData", daosrv.showHowToClaim());
		return "claimresults";
	}
	*/

}
