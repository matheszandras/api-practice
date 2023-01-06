package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.herokuapp.restfulbooker.CreateBooking.createBooking;

public class DeleteBookingTest {

    @Test
    public void DeleteBookingTest() {

        Response responseCreate = createBooking();
        responseCreate.prettyPrint();
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        Response responseDelete = RestAssured.given().auth().preemptive().basic("admin", "password123").delete("http://localhost:3001/booking/" + bookingId);
        responseDelete.prettyPrint();

        Assert.assertEquals(responseDelete.getStatusCode(), 201, "Status code is not as expected");

        Response responseDeleted = RestAssured.get("http://localhost:3001/booking/" + bookingId);
        Assert.assertEquals(responseDeleted.getStatusCode(),404);
    }
}
