package com.abhay.demo.mvc.component.validator;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.abhay.demo.model.User;
import com.abhay.demo.request.LoginRequest;
import com.abhay.demo.service.DefaultTransactionalService;


@Component
public class LoginValidator implements Validator {

	@Autowired
	private DefaultTransactionalService defaultTransactionalService;
	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}

	public Errors validatesRequest(@NotNull LoginRequest userLoginRequest, @NotNull Errors errors) {
		if (StringUtils.isEmpty(userLoginRequest.getUsername())) {
			errors.reject("invalid.username", "Mobile No. can't be blank");
			return errors;
		}
		if (StringUtils.isEmpty(userLoginRequest.getPassword())) {
			errors.reject("invalid.password", "Password can't be blank");
			return errors;
		}

		
		
		User userInfo = defaultTransactionalService.getUserInfoByMobileAndStatus(userLoginRequest.getUsername(),true);
		if (Objects.isNull(userInfo)) {
			errors.reject("invalid.username", "Invalid mobile/ password");
			return errors;
		}
		
	
		if (!matchPassword(userLoginRequest.getPassword(), userInfo.getPassword())) {
			
			errors.reject("invalid.password", "Invalid mobile/password");
			return errors;
		}
		return errors;
	}

	public String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);

	}

	public boolean matchPassword(String newPassword, String ecodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(newPassword, ecodedPassword);
	}

}
