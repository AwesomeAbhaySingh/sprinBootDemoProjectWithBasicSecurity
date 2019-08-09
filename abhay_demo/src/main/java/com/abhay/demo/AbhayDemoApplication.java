package com.abhay.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableAutoConfiguration
public class AbhayDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AbhayDemoApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		// return super.configure(builder);
		return builder.sources(AbhayDemoApplication.class);
	}
}
