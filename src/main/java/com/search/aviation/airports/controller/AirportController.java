package com.search.aviation.airports.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.search.aviation.airports.model.ModelAirport;

import com.search.aviation.airports.service.AirportService;

@RestController     // RestController=Controller+ResponseBody
public class AirportController {
	/*
	@RequestMapping("/{name}")
	public String hello(@PathVariable String name){
		return "Hello "+ name;
	}
    */
	@Autowired
	private AirportService airportService;
	
	@RequestMapping("/airports")
	public List<ModelAirport> modelAirport(){
		
		List<ModelAirport> modelAirport=airportService.getAirportList();
		
		return modelAirport;	
	}
	
	@RequestMapping("/airports/code/{code}")
	public ModelAirport modelAirportCode(@PathVariable String code){
		
		List<ModelAirport> modelAirport=airportService.getAirportList();
		
		for(ModelAirport airport: modelAirport){
			String cod = airport.getCode();
			if(cod.equals(code)){
				return airport;
			}
		}
		
		return null;
		
	}
	
			@RequestMapping("/airports/search")
			public List<ModelAirport> airportSearch(@RequestParam(required=false,name="q")String deger) {
				
				List<ModelAirport> modelAirport = airportService.getAirportList();
				List<ModelAirport> bulunan = new ArrayList<>();
				
				for(ModelAirport airport:modelAirport){
					
					String name1=airport.getName();
					String city1=airport.getCity();
					String country1=airport.getCountry();
					String code1=airport.getCode();
					String latitude1=airport.getLatitude();
					String longtitude1=airport.getLongitude();
					
					if(name1.equals(deger) || city1.equals(deger) || country1.equals(deger) || code1.equals(deger)  || latitude1.equals(deger) || longtitude1.equals(deger)){
						bulunan.add(airport);
					}	
				}
						
			    return bulunan;
			}
				
			@RequestMapping(value="/airports",method=RequestMethod.POST)
			public Boolean saveNewAirport(@RequestBody ModelAirport airport){
				return airportService.createAirport(airport);
			}
	
			@RequestMapping(value="/airports/{id}",method=RequestMethod.PUT)   //update
			public boolean updateNewAirport(@RequestBody ModelAirport airport,@PathVariable int id){
				return airportService.updateAirport(airport, id);
			}
			
			@RequestMapping(value="/airports/{id}", method=RequestMethod.DELETE)
			public boolean deleteNewAirport(@PathVariable int id){
				return airportService.deleteAirport(id);
			}		
			
}

