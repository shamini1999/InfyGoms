package com.infy.infygo.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.infygo.booking.entity.Ticket;
import com.infy.infygo.booking.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public void createTicket(Ticket ticket) {

		ticketRepository.saveAndFlush(ticket);

	}

}
