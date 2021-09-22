package com.infy.infygo.flight.service;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.infy.infygo.flight.controller.FeignInterface;
import com.infy.infygo.flight.dto.Passenger;
import com.infy.infygo.flight.dto.SearchFlights;
import com.infy.infygo.flight.exception.ARSServiceException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Service
public class HystrixService {

	@Autowired 
	DiscoveryClient client;
	
	@Autowired
	FeignInterface feign;
	@HystrixCommand
	public Future<List<Passenger>> getFlights1(String flightId) throws ARSServiceException {
		return new AsyncResult<List<Passenger>>() {
			
		@Override
		public List<Passenger> invoke(){
		List<ServiceInstance> bookingInstance=client.getInstances("BookingMS");
		ServiceInstance bi=bookingInstance.get(0);
		URI BookingUri=bi.getUri();
		System.out.println(BookingUri);
		//return feign.getFlights1(get());
		return new RestTemplate().getForObject(BookingUri+"/flights/F101/book/passengerList", List.class);
	}
	};
	}
	
	
}
