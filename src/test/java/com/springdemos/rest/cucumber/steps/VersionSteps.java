package com.springdemos.rest.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;

import com.springdemos.rest.RestCucumberApplication;
import com.springdemos.rest.cucumber.ResponseResults;
import com.springdemos.rest.cucumber.context.CucumberContext;

@ContextConfiguration(classes = RestCucumberApplication.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class VersionSteps {

	@Autowired
	CucumberContext context;

	@Autowired
	ResponseErrorHandler responseErrorHandler;
	
	@When("^the client calls /version$")
	public void the_client_issues_GET_version() throws Throwable {
		executeGet("http://localhost:8080/version");
	}

	@Then("^the client receives status code of (\\d+)$")
	public void the_client_receives_status_code_of(int statusCode) throws Throwable {
		HttpStatus currentStatusCode = context.getLatestResponse().getTheResponse()
				.getStatusCode();
		assertThat("status code is incorrect : " + context.getLatestResponse().getBody(), currentStatusCode.value(), is(statusCode));
	}

	@And("^the client receives server version (.+)$")
	public void the_client_receives_server_version_body(String version) throws Throwable {
		assertThat(context.getLatestResponse().getBody(), is(version));
	}

	private void executeGet(String url) {
		final Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
		
		context.setLatestResponse((ResponseResults) context.getRestTemplate()
				.execute(url, HttpMethod.GET, requestCallback, response ->
					{
						if (responseErrorHandler.hasError(response)) {
							return (null);
						} else {
							return (new ResponseResults(response));
						}
					}));
	}
	
	private class HeaderSettingRequestCallback implements RequestCallback {
	    final Map<String, String> requestHeaders;

	    private String body;

	    public HeaderSettingRequestCallback(final Map<String, String> headers) {
	        this.requestHeaders = headers;
	    }

	    public void setBody(final String postBody) {
	        this.body = postBody;
	    }

	    @Override
	    public void doWithRequest(ClientHttpRequest request) throws IOException {
	        final HttpHeaders clientHeaders = request.getHeaders();
	        for (final Map.Entry<String, String> entry : requestHeaders.entrySet()) {
	            clientHeaders.add(entry.getKey(), entry.getValue());
	        }
	        if (null != body) {
	            request.getBody().write(body.getBytes());
	        }
	    }

	}
}
