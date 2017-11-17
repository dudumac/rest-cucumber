package com.springdemos.rest.cucumber.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.springdemos.rest.cucumber")
public class CucumberConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
//		final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
		restTemplate.setErrorHandler(responseErrorHandler());
		return restTemplate;
	}
	
	@Bean
	public ResponseErrorHandler responseErrorHandler() {
		return new DefaultResponseErrorHandler();
	}

}
