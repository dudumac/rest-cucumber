package com.springdemos.rest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//TODO - How does Cucumber find the "glue"
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class IntegrationTest {
}
