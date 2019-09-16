package com.search.aviation.airports.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.search.aviation.airports.model.ModelAirport;
import com.search.aviation.airports.service.AirportService;
import java.util.List;

@Controller
public class ViewController {
	
	private Integer limit = 10;

	@Autowired
	private AirportService airportService;
	
	//Html deki formu getir
	@RequestMapping(value = "/AirportForm", method = RequestMethod.GET)
	public String inde(Model model, @RequestParam(name="page",required=false) Integer page ) {
		if(page==null){
			List<ModelAirport> modelAirportList = airportService.getAirportList();
			model.addAttribute("modelAirportList", modelAirportList);
			
		}
		else{
			
			List<ModelAirport> modelAirportList2 = airportService.getAirportList();
			//model.addAttribute("modelAirportLisst2",modelAirportList2);
			double totalPage=(modelAirportList2.size())/limit;
			
		    if((modelAirportList2.size())%limit!= 0){
		       
		    	totalPage = Math.ceil(totalPage);
		    } 
		    model.addAttribute("totalPage",totalPage);  //htmlde totalPage ye ihtiyacım var. bunun icin
		    
		    int startPage = (page*limit)-limit;  //kacinci sayfadan baslayacagi
		    int endpage = (page*limit); //kacinci sayfada bitecegi
		    endpage = Math.min(modelAirportList2.size(), endpage);
		   // if(page>0 && page<=totalPage){ }
		    	
		    	List<ModelAirport> newList =  modelAirportList2.subList(startPage, endpage);  //iki deger arasindaki kismi al
		    	model.addAttribute("modelAirportList", newList); //belli bir kismi ekliyor
		    
		    
		    /*
		    for(int i=(page-1)*limit; i<((page-1)*limit)+limit; i++){
		    	//model.addAttribute("modelAirportList2",modelAirportList2);
		    	 yeni bir list olusturup modelAi2.get(i) deyip atayabilirdin bunun yeerine subList kullandim
		    	
		    }
			*/
			
		}
		
		return "AirportForm";
	}

		

}
