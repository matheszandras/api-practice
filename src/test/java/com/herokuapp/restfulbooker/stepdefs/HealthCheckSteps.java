package com.herokuapp.restfulbooker.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;

public class HealthCheckSteps {
    protected RequestSpecification spec;
    private RequestSpecification request;
    private Response response;

    @Given("The API base URI is configured")
    public void setupRequest() {
        request = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();
    }

    @Given("I set the following headers:")
    public void setHeaders(Map<String, String> headers) {
        request.headers(headers);
    }

    @Given("I set the following cookies:")
    public void setCookies(Map<String, String> cookies) {
        request.cookies(cookies);
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = request.log().all().get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
        System.out.println("Checking if status code is: " + statusCode);
    }

    @Then("the response should contain a {string} header")
    public void verifyHeader(String headerName) {
        response.then().header(headerName, notNullValue());
        String headerValue = response.getHeader(headerName);
        System.out.println(headerName + ": " + headerValue);
    }

    @Then("I log the response headers and cookies")
    public void logDetails() {
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("Cookies: " + response.getDetailedCookies());
    }
}
