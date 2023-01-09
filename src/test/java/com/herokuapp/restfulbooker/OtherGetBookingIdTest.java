package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OtherGetBookingIdTest extends BaseTest {
    @Test
    public void OtherGetBooking() {
        spec.pathParam("bookingId",5);
        Response response = RestAssured.given(spec).get("booking/{bookingId}");

        response.prettyPrint();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.jsonPath().get("firstname"), "Eric", "Firstname doesn't match!");
        softAssert.assertEquals(response.jsonPath().get("lastname"), "Smith", "Lastname doesn't match");

        int totalPrice = response.jsonPath().get("totalprice");
        Assert.assertEquals(totalPrice, 282, "Total price is not as expected");
        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        Assert.assertFalse(depositPaid, "Depositpaid is not as expected");

        String actualCheckIn = response.jsonPath().get("bookingdates.checkin");
        Assert.assertEquals(actualCheckIn, "2022-03-27", "Check-in date is not as expected");
        String actualCheckOut = response.jsonPath().get("bookingdates.checkout");
        Assert.assertEquals(actualCheckOut, "2022-05-31", "Checkout date is not as expected");

        Assert.assertEquals(response.jsonPath().get("additionalneeds"), "Breakfast", "Additipnal needs is not as expected");

        softAssert.assertAll();
    }
}
