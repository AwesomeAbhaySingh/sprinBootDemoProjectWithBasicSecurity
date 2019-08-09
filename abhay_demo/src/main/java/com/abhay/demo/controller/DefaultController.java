package com.abhay.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abhay.demo.model.TestRegistration;
import com.abhay.demo.request.RegistrationRequest;
import com.abhay.demo.response.RegistrationResponse;
import com.abhay.demo.service.DefaultService;
import com.abhay.demo.utils.CustomErrorObject;
import com.abhay.demo.utils.ResponseEntityObject;

@RestController
@RequestMapping("demo")
public class DefaultController {

	@Autowired
	private DefaultService defaultService;

	@RequestMapping(value = "/get-record", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseEntityObject> getrecord(HttpServletRequest httpServletRequest) {

		ResponseEntityObject response = new ResponseEntityObject();

		List<RegistrationResponse> list = defaultService.getAllRecord();

		response.setMessage("Success");
		response.setData(list);
		response.setStatusCode(String.valueOf(200));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseEntityObject> saverecord(HttpServletRequest httpServletRequest,@RequestBody RegistrationRequest registrationRequest) {

		CustomErrorObject errors = new CustomErrorObject();
		ResponseEntityObject response = new ResponseEntityObject();

		if (StringUtils.isEmpty(registrationRequest.getName())) {
			response.setMessage("Please Write Somthing...");
			response.setStatusCode(String.valueOf(422));
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (StringUtils.isEmpty(registrationRequest.getCity())) {
			response.setMessage("Please Write Somthing...");
			response.setStatusCode(String.valueOf(422));
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		defaultService.saveTestRegistrations(registrationRequest);

		response.setMessage("Success");
		response.setStatusCode(String.valueOf(200));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
