package com.infy.infygo.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.infygo.payment.entity.CreditCardDetails;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardDetails, String> {

}
