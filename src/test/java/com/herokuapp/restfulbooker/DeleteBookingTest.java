package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseTest{
    @Test
    public void DeleteBookingTest() {

        Response responseCreate = createBooking();
        responseCreate.prettyPrint();
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        Response responseDelete = RestAssured.given().auth().preemptive().basic("admin", "password123").delete("https://restful-booker.herokuapp.com/booking/" + bookingId);
        responseDelete.prettyPrint();

        Assert.assertEquals(responseDelete.getStatusCode(), 201, "Status code is not as expected");

        Response responseDeleted = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingId);
        Assert.assertEquals(responseDeleted.getStatusCode(),404);
    }
}
