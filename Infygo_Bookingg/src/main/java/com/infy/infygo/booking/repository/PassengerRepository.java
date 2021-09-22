package com.infy.infygo.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.infygo.booking.entity.Passenger;



@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	

}
