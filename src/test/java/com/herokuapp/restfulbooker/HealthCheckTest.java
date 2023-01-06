package com.herokuapp.restfulbooker;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckTest {
    @Test
    public void HealthChackTest() {
        given().when().get("http://localhost:3001/ping")
                .then().assertThat().statusCode(201);


    }
}
