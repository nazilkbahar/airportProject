package com.search.aviation.airports.service;

import com.search.aviation.airports.model.ModelAirport;
import java.util.List;

public interface AirportService {
	
	 List<ModelAirport> getAirportList();
	 boolean createAirport(ModelAirport airport);
	 boolean updateAirport(ModelAirport airport,int id);
	 boolean deleteAirport(int id);

}