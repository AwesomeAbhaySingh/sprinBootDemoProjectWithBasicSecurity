package com.abhay.demo.mvc.component.constant;

public enum WebPages {

	INDEX("index"), ERROR("error"), CONTACT_US("contact"), LOGIN("login"), DOSHBOARD("dashboard");

	private final String jsp_name;

	private WebPages(String jsp_name) {
		this.jsp_name = jsp_name;
	}

	public String getJsp_name() {
		return jsp_name;
	}

}
