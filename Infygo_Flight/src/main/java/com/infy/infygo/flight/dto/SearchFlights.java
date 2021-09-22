/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infy.infygo.flight.dto;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


import com.infy.infygo.flight.entity.Flight;

public class SearchFlights {

	@NotEmpty(message = "Please enter the origin")
	@NotNull(message = "Soource is mandatory")
	private String source;

	@NotEmpty(message = "Please enter the destination")
	@NotNull(message = "Destination is mandatory")
	private String destination;
	@NotEmpty(message = "Please enter the journey date")
	@NotNull(message = "Journey date is mandatory")
	private Date journeyDate;

	private String fare;
	private String flightId;
   
    List<Passenger> passengerList;
    



	

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	private String departureTime;
	private String arrivalTime;
	private String airlines;
	private String seatCount;
	
	

	
	

	

	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}

	

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	


	public String getSource() {
		return source;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date calendar) {
		this.journeyDate = calendar;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	

	

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	// Converts Entity into DTO
			public static SearchFlights valueOf(Flight cust) {
				SearchFlights sdto = new SearchFlights();
				sdto.setAirlines(cust.getAirlines());
			     sdto.setFlightId(cust.getFlightId());
				sdto.setDestination(cust.getDestination());
				 sdto.setFare(Double.toString(cust.getFare()));
				 sdto.setArrivalTime(cust.getArrivalTime());
				 sdto.setDepartureTime(cust.getDepartureTime());
				 sdto.setSeatCount(cust.getSeatCount().toString());
				 sdto.setJourneyDate(cust.getFlightAvailableDate());
				 sdto.setSource(cust.getSource());
				
				
				
				
				
				return sdto;
			}

		
		
			
			
}
