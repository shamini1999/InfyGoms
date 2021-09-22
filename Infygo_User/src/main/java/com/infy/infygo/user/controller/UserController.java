package com.infy.infygo.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.infygo.user.dto.LoginDetails;
import com.infy.infygo.user.entity.Customer;
import com.infy.infygo.user.exception.InfyGoServiceException;
import com.infy.infygo.user.service.LoginService;
import com.infy.infygo.user.service.RegistrationService;


@RestController
@EnableDiscoveryClient
public class UserController {
	@Autowired
	LoginService loginService;

	@Autowired
	RegistrationService registrationService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Boolean> Validate(@Valid @RequestBody LoginDetails login) throws InfyGoServiceException {
		Boolean result = false;
		result = loginService.isUserValid(login);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Boolean> customer(@RequestBody Customer customer) throws InfyGoServiceException {
		Boolean result = registrationService.insertUser(customer);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
