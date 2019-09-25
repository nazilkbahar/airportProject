package com.search.aviation.airports.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.search.aviation.airports.model.ModelAirport;


public class AirportHelper {
	
	public static List<ModelAirport> createAirportList(String response){
		
		ArrayList<ModelAirport> list=new ArrayList<>();
		String[] airportRow=response.split("\n");
		int rowCount=airportRow.length;
		
		for(int i=0; i<rowCount; i++){
			String line=airportRow[i];
			ModelAirport a=createAirportFromLine(line);
			list.add(a);
		}
		
		return list;
	}
	
	public static String deleteTirnak(String line){
		
		String tirnak="\"";
		String bosluk="";
		line=line.replace(tirnak, bosluk);
		
	   return line;
	}
	
	public static ModelAirport createAirportFromLine(String airportLine){
		
		String [] airportLines=airportLine.split(",");
		int lineCount=airportLines.length;
		//System.out.println(lineCount);
		
		for(int i=0; i<lineCount; i++){
			airportLines[i]=deleteTirnak(airportLines[i]);
		}   
		
		ModelAirport modelAirport=new ModelAirport();
		for(int i=0; i< lineCount; i++) {
		       
		    if(i==0){
		    	
		    	modelAirport.setId(airportLines[i]);
		    }
		    else if(i==1){
		    	modelAirport.setName(airportLines[i]);
		    }
		    else if(i==2){
		    	modelAirport.setCity(airportLines[i]);
		    }
		    else if(i==3){
		    	modelAirport.setCountry(airportLines[i]);
		    }
		    else if(i==4){
		    	 modelAirport.setCode(airportLines[i]); 
		    }
		    else if(i==6){
		    	 modelAirport.setLatitude(airportLines[i]); 
			}
		    else if(i==7){
		    	modelAirport.setLongitude(airportLines[i]);
			}
		    else {
				continue;
			}   
		}
		
		return modelAirport; 
	} 
		
}
