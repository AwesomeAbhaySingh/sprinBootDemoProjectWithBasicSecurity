package com.abhay.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhay.demo.model.TestRegistration;
import com.abhay.demo.request.RegistrationRequest;
import com.abhay.demo.response.RegistrationResponse;

// this is the business login class

@Service
public class DefaultService {

	@Autowired
	private DefaultTransactionalService defaultTransactionalService;

	public void saveTestRegistrations(RegistrationRequest registrationRequest) {

		TestRegistration testRegistration = new TestRegistration();
		testRegistration.setName(registrationRequest.getName());
		testRegistration.setCity(registrationRequest.getCity());
		defaultTransactionalService.saveOrUpDate(testRegistration);

	}

	public List<RegistrationResponse> getAllRecord() {

		List<RegistrationResponse> responseList = new ArrayList<RegistrationResponse>();
		List<TestRegistration> testRegistrationList = defaultTransactionalService.getAllRecord();
		if (!testRegistrationList.isEmpty()) {
			testRegistrationList.parallelStream().filter(record -> !Objects.isNull(record)).forEachOrdered(record -> {

				RegistrationResponse registrationResponse = new RegistrationResponse();
				registrationResponse.setCity(record.getCity());
				registrationResponse.setName(record.getName());
				responseList.add(registrationResponse);
			});
		}

		return responseList;

	}

}
