package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PartialUpdateBookingTest extends BaseTest {

    @Test
    public void PartialUpdateBookingTest() {

        Response responseCreate = createBooking();
        responseCreate.prettyPrint();
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        JSONObject body = new JSONObject();
        body.put("firstname", "Jacques");
        body.put("bookingdates.checkin", "2023-01-03");

        Response responseUpdate = RestAssured.given().auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).
                body(body.toString()).patch("http://localhost:3001/booking/" + bookingId);
        responseUpdate.prettyPrint();

        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Status code is not as expected");

        Assert.assertEquals(responseUpdate.jsonPath().getString("firstname"), "Jacques", "firstname is not as expected (Andras)");
        Assert.assertEquals(responseUpdate.jsonPath().get("bookingdates.checkin"), "2023-01-03", "checkin date is not as expected");
    }
}
