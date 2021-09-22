
package com.infy.infygo.booking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.infygo.booking.utility.*;

import com.infy.infygo.booking.dto.BookingDetails;
import com.infy.infygo.booking.dto.PassengerDetails;
import com.infy.infygo.booking.entity.Flight;
import com.infy.infygo.booking.entity.Passenger;
import com.infy.infygo.booking.entity.Ticket;
import com.infy.infygo.booking.exception.ARSServiceException;
import com.infy.infygo.booking.exception.ExceptionConstants;
import com.infy.infygo.booking.exception.InfyGoServiceException;
import com.infy.infygo.booking.service.FlightService;
import com.infy.infygo.booking.service.PassengerService;
import com.infy.infygo.booking.service.TicketService;


@RestController

@EnableDiscoveryClient
public class BookingController {

	protected Logger logger = Logger.getLogger(BookingController.class.getName());

	@Autowired
	private FlightService flightService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private PassengerService passengerService;
	private Ticket ticket;
	private int noOfSeats;

	public BookingController() {
		ticket = new Ticket();
	}
	
	@GetMapping(value = "/flights/{flightId}/book/passengerList",  produces= MediaType.APPLICATION_JSON_VALUE)
	
	public List<Passenger> getPassengers(@PathVariable String flightId) throws ARSServiceException {
		System.out.println("In p");
		if(flightId.equals("F105")) {
			throw new RuntimeException();
		}
		return passengerService.getPassengers();
	}

	@PostMapping(value = "/book/{flightId}/{username}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BookingDetails> bookFlight(@PathVariable("flightId") String flightId,
			@Valid @RequestBody PassengerDetails passengerDetails, @PathVariable("username") String username,
			Errors errors) throws InfyGoServiceException, ARSServiceException {

		if (errors.hasErrors()) {
			return new ResponseEntity(new ClientErrorInformation(HttpStatus.BAD_REQUEST.value(),
					errors.getFieldError("passengerList").getDefaultMessage()), HttpStatus.BAD_REQUEST);
		}
		if (passengerDetails.getPassengerList().isEmpty())
			throw new InfyGoServiceException(ExceptionConstants.PASSENGER_LIST_EMPTY.toString());

		List<Passenger> passengerList = new ArrayList<Passenger>();
		for (Passenger passengers : passengerDetails.getPassengerList()) {
			passengerList.add(passengers);

		}
		System.out.println(passengerList.toString());

		logger.log(Level.INFO, "Book Flight method ");

		logger.log(Level.INFO, passengerDetails.toString());
		int pnr = (int) (Math.random() * 1858955);

		ticket.setPnr(pnr);
//		Date date = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);

		Flight flight = flightService.getFlights(flightId);

		double fare = flight.getFare();
		System.out.println("Fare per person:****** " + fare);
		System.out.println("List size:****** " + passengerDetails.getPassengerList().size());
		double totalFare = fare * (passengerDetails.getPassengerList().size());

		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setPassengerList(passengerDetails.getPassengerList());
		bookingDetails.setPnr(pnr);
		bookingDetails.setTotalFare(totalFare);
		ticket.setBookingDate(new Date());
		System.out.println(ticket.getBookingDate());
		ticket.setDepartureDate(flight.getFlightAvailableDate());
		ticket.setDepartureTime(flight.getDepartureTime());
		ticket.setFlightId(flight.getFlightId());
		ticket.setUserId(username);
		ticket.setTotalFare(totalFare);
		noOfSeats = passengerDetails.getPassengerList().size();
		ticket.setNoOfSeats(noOfSeats);
		ticketService.createTicket(ticket);

		addPassengers(bookingDetails.getPassengerList());

		flightService.updateFlight(flightId, noOfSeats);

		return new ResponseEntity<BookingDetails>(bookingDetails, HttpStatus.OK);

	}

	private void addPassengers(List<Passenger> passengers) {

		for (Passenger passenger : passengers) {
			passenger.setTicket(ticket);

		}

		passengerService.createPassenger(passengers);

	}

}
