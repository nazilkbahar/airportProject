package com.search.aviation.airports.model;

public class ModelAirport {
	
	private String id;
	private String name;
	private String city;
	private String country;
	private String code;
	private String latitude;
	private String longitude;
	
	public String getId() {
		return id;
	}
	public void setId(String airportLines) {
		this.id = airportLines;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "ModelAirport [id=" + id + ", name=" + name + ", city=" + city + ", country=" + country + ", code="
				+ code + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}


}
