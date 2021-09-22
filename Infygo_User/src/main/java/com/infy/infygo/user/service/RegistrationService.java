/**
 * 
 */
package com.infy.infygo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.infygo.user.entity.Customer;
import com.infy.infygo.user.exception.InfyGoServiceException;
import com.infy.infygo.user.repository.CustomerRepository;

@Service
public class RegistrationService {

	@Autowired
	private CustomerRepository customerRepository;

	public Boolean insertUser(Customer customer) throws InfyGoServiceException {

		Customer cust = customerRepository.saveAndFlush(customer);

		if (cust == null) {
			throw new InfyGoServiceException("User record not found");
		} else {
			return true;
		}

	}

}
