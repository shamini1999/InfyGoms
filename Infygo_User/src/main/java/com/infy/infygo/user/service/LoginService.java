
package com.infy.infygo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.infygo.user.dto.LoginDetails;
import com.infy.infygo.user.entity.Customer;
import com.infy.infygo.user.exception.ExceptionConstants;
import com.infy.infygo.user.exception.InfyGoServiceException;
import com.infy.infygo.user.repository.CustomerRepository;

@Service
public class LoginService {

	@Autowired
	private CustomerRepository customerRepository;

	public boolean isUserValid(LoginDetails customerLogin) throws InfyGoServiceException {

		Customer customer = customerRepository.findById(customerLogin.getUserId()).get();

		if (customer == null) {
			throw new InfyGoServiceException(ExceptionConstants.USER_NOT_FOUND.toString(), "User record not found");
		} else if (!(customer.getPassword().equals(customerLogin.getPassword()))) {
			throw new InfyGoServiceException(ExceptionConstants.USER_INVALID.toString(), "Invalid credentials");
		}
		return true;

	}

}
