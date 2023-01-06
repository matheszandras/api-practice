package com.herokuapp.restfulbooker;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.herokuapp.restfulbooker.CreateBooking.createBooking;

public class CreateBookingTest {

    @Test
    public void CreateBookingTest() {

        Response response = createBooking();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not as expected");

        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Andras", "firstname is not as expected (Andras)");
        Assert.assertEquals(response.jsonPath().get("booking.lastname"), "LeClo", "lastname is not as expected (Leclo)");
        Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"), 120, "Price is not as expected (120)");
        Assert.assertEquals(response.jsonPath().getBoolean("booking.depositpaid"), true, "Paid deposit should be true but it is not");
        Assert.assertEquals(response.jsonPath().get("booking.bookingdates.checkin"), "2022-01-04", "checkin date is not as expected");
        Assert.assertEquals(response.jsonPath().get("booking.bookingdates.checkout"),"2030-12-31", "checkout date is not as expected");
        Assert.assertEquals(response.jsonPath().get("booking.additionalneeds"),"uranium, 41st field artillery regiment", "additional needs are not as expected");


    }

}
