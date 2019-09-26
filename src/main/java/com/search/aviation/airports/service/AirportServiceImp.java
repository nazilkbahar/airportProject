package com.search.aviation.airports.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.search.aviation.airports.model.ModelAirport;

@Service
public class AirportServiceImp implements AirportService{
	
	private List<ModelAirport> airportList;
	
	@PostConstruct
	public void initAirportList(){
		
		RestTemplate template = new RestTemplate();
		//ResponseEntity<String> entity =  template.getForEntity("http://10.26.12.227:3002/airports.dat", String.class);
		//System.out.println(entity.getBody());

		//ResponseEntity<String> entityy =  template.getForEntity("http://10.26.12.227:3002/airportsmini.dat", String.class);
		
		
		String airportLine = "11844,\"Brunswick Executive Airport\",\"Brunswick\",\"United States\",\"NHZ\",\"KNHZ\",43.89220047,-69.93859863,72,\\N,\\N,\\N,\"airport\",\"OurAirports\""; //deneme
		ModelAirport modelAirport = AirportHelper.createAirportFromLine(airportLine);
		airportList=new ArrayList<>();
		airportList.add(modelAirport);
		//airportList = AirportHelper.createAirportList(entityy.getBody());
		
	}
	
	@Override
	public List<ModelAirport> getAirportList() {

		return airportList;
	}

	@Override
	public boolean createAirport(ModelAirport airport) {
		if(airport!=null && airport.getId().equals("0")){
			return false;
		}
		airportList.add(airport);
		return true;
	}

	@Override
	public boolean updateAirport(ModelAirport airport, int id) {
	for(int i=0; i<airportList.size(); i++){
		
			ModelAirport t=airportList.get(i);
			
			if(t.getId().equals(""+id)){        //id'lerine e gore 
				t.setName(airport.getName());
				airportList.set(i, t);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteAirport(int id) {
	System.out.println(airportList.size()+" size before delete");
	    for(int i=0; i<airportList.size(); i++){
				
			if(	airportList.removeIf(t -> t.getId().equals(""+id))){
				System.out.println(airportList.size()+" size just before true ");
				return true;
			}
				
			}
			return false;
	}
}
