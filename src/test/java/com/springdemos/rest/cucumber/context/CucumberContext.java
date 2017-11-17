package com.springdemos.rest.cucumber.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springdemos.rest.cucumber.ResponseResults;

@Component
public class CucumberContext {

    ResponseResults latestResponse = null;

	@Autowired
	private RestTemplate restTemplate;

    public ResponseResults getLatestResponse() {
		return latestResponse;
	}

	public void setLatestResponse(ResponseResults latestResponse) {
		this.latestResponse = latestResponse;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
}
