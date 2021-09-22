package com.infy.infygo.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.infygo.booking.entity.Flight;
import com.infy.infygo.booking.entity.Passenger;
import com.infy.infygo.booking.exception.ARSServiceException;
import com.infy.infygo.booking.repository.PassengerRepository;


@Service
public class PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	public void createPassenger(List<Passenger> passengers) {

		passengerRepository.saveAll(passengers);

	}
	public List<Passenger> getPassengers() throws ARSServiceException {
		return passengerRepository.findAll();
	}

}
