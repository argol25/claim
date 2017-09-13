package pl.arek.claiming.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import pl.arek.claiming.ClaimDAO;
import pl.arek.claiming.ResultDAO;
import pl.arek.claiming.domain.ClaimData;
import pl.arek.claiming.dto.ClaimDataDTO;
import pl.arek.claiming.DataProcessing;


@Controller
public class ClaimingController {
	
	@Autowired
	ClaimDAO cdao;
	@Autowired
	ResultDAO rdao;
	
	@RequestMapping("/claimdata")
	public String addClaimData(HttpServletRequest request, @ModelAttribute("claimDto") @Valid ClaimDataDTO claimDto, BindingResult result){
		
		if(request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()){
			ClaimData cd = new ClaimData();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			
			try{
				cd.setStartTime(sdf.parse(claimDto.getStartTime()));
				cd.setEndTime(sdf.parse(claimDto.getEndTime()));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			cd.setAccountName(claimDto.getAccountName());
			cd.setStartHour(claimDto.getStartTime());
			cd.setEndHour(claimDto.getEndTime());
			cd.setSb1Day(claimDto.getSb1Day());
			cd.setSb2Day(claimDto.getSb2Day());
			
			//do skasowania
			cdao.addBasicData(cd);
			//do skasowania
			cdao.getClaiming();
			
			//DataProcessing dp = new DataProcessing(cd.getStartTime().toString(), cd.getEndTime().toString(), cd.getStartHour(), cd.getEndHour(), cd.getSb1Day(), cd.getSb2Day(), cd.getAccountName()); 
			DataProcessing dp = new DataProcessing(claimDto.getStartTime(),claimDto.getEndTime(),claimDto.getStHour(), claimDto.getEndHour(), claimDto.getSb1Day(), claimDto.getSb2Day(), claimDto.getAccountName());
			dp.claimDataCount();
			
			
			
			return "claimdata";
		}
		
		
		return "/claimdata";
	}
	
	@RequestMapping("/claimresults")
	public String claimList(Model model){
		model.addAttribute("resData", rdao.getClaim());
		return "claimresults";
	}
	
}
