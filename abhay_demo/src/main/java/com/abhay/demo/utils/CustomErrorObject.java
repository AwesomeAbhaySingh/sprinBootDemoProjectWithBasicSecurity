package com.abhay.demo.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public class CustomErrorObject {

	private HttpStatus httpStatus;
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean hasError() {
		return (!StringUtils.isEmpty(getError()));
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
