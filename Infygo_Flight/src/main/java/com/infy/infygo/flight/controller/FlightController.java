/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infy.infygo.flight.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.infy.infygo.config.FlightRibbonConfig;
import com.infy.infygo.flight.dto.Passenger;
import com.infy.infygo.flight.dto.SearchFlights;
import com.infy.infygo.flight.entity.Flight;
import com.infy.infygo.flight.exception.ARSServiceException;
import com.infy.infygo.flight.service.FlightService;
import com.infy.infygo.flight.service.HystrixService;
import com.infy.infygo.flight.utility.MyDateEditor;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
//@RibbonClient (name="flightribbon",configuration=FlightRibbonConfig.class)
@RequestMapping("/flights")
@EnableDiscoveryClient
public class FlightController {

	protected Logger logger = Logger.getLogger(FlightController.class.getName());

	@Autowired
	private FlightService flightService;
	@Autowired
	private HystrixService hys;
//	@Value("${Booking.uri}")
//	String BookingUri;
//
//	@Autowired
//	RestTemplate template;
//	
//	@Autowired 
//	DiscoveryClient client;
//	
	@InitBinder
	public void myInitBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new MyDateEditor(format));
	}

	@HystrixCommand(fallbackMethod="getFlightsFallback")
	@GetMapping("/{flightId}")
	public SearchFlights getFlights(@PathVariable("flightId") String flightId) throws ARSServiceException, InterruptedException, ExecutionException {
		System.out.println("data fetched1");
		System.out.println("flight id" + flightId);
     	SearchFlights ss=flightService.getFlights(flightId);
//		List<ServiceInstance> bookingInstance=client.getInstances("BookingMS");
//		ServiceInstance bi=bookingInstance.get(0);
//		URI BookingUri=bi.getUri();
//		System.out.println(BookingUri);
		
		@SuppressWarnings("unchecked")
		Future<List<Passenger>> passenger=hys.getFlights1(flightId);
		ss.setPassengerList(passenger.get());
		
		
		System.out.println(ss);
		return ss;
		
		
	}
   
	public SearchFlights getFlightsFallback(@PathVariable("flightId") String flightId) throws ARSServiceException {
		return new SearchFlights();
	}
	
	@RequestMapping(value = "/sources", method = RequestMethod.GET)
	public List<String> getFlightSources() throws ARSServiceException {
		System.out.println("In get sources");
		return flightService.getSources();
	}

	@RequestMapping(value = "/destinations", method = RequestMethod.GET)
	public List<String> getFlightDestinations() throws ARSServiceException {
		System.out.println("In get sources");
		return flightService.getDestinationss();
	}

	@RequestMapping(value = "/{source}/{destination}/{journeyDate}", method = RequestMethod.GET)
	public ResponseEntity<List<SearchFlights>> getFlightDetails(@PathVariable String source,
			HttpServletResponse response, @PathVariable String destination, @PathVariable Date journeyDate) {
		List<SearchFlights> availableFlights = flightService.getFlights(source, destination, journeyDate);
		return new ResponseEntity<List<SearchFlights>>(availableFlights, HttpStatus.OK);

	}

	@RequestMapping(value = "/{flightId}/{noOfSeats}")
	public void updateFlightSeat(@PathVariable String flightId, @PathVariable int noOfSeats)
			throws ARSServiceException {
		flightService.updateFlight(flightId, noOfSeats);

	}
}