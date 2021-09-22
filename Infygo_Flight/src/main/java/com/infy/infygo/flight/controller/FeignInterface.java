package com.infy.infygo.flight.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infy.infygo.flight.dto.Passenger;
import com.infy.infygo.flight.dto.SearchFlights;

@FeignClient("BookingMS")
public interface FeignInterface {
    @RequestMapping(value="/flights/{flightId}/book/passengerList")
    List<Passenger> getFlights1(@PathVariable("flightId") List<Passenger> passengerList);
}
