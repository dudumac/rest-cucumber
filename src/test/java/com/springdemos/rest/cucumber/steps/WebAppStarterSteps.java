package com.springdemos.rest.cucumber.steps;

import org.assertj.core.util.Arrays;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.springdemos.rest.RestCucumberApplication;

import cucumber.api.java8.En;

 
@ContextConfiguration(classes = RestCucumberApplication.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class WebAppStarterSteps implements En {

	public WebAppStarterSteps() {
		Before(Arrays.array("@webcontainerstarted"), () -> {});	
	}
}
