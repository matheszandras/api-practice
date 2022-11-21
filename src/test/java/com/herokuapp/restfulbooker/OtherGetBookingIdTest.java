package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OtherGetBookingIdTest {
    @Test
    public void OtherGetBooking() {
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/8");
        response.print();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().get("firstname"),"Mark","Firstname doesn't match!");

    }
}
