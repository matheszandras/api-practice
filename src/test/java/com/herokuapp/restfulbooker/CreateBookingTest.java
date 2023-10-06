package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBookingTest extends BaseTest {

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
        Assert.assertEquals(response.jsonPath().get("booking.bookingdates.checkout"), "2030-12-31", "checkout date is not as expected");
        Assert.assertEquals(response.jsonPath().get("booking.additionalneeds"), "uranium, 41st field artillery regiment", "additional needs are not as expected");

    }

    @Test
    public void CreateBookingWithPojoTest() {
        Bookingdates bookingdates = new Bookingdates("2022-01-05", "2022-07-12");
        Booking bookingBody = new Booking("Andras", "LeFou", 128, true, bookingdates, "Rockwell B-1 Lancer");


        Response response2 = RestAssured.given(spec).contentType(ContentType.JSON).body(bookingBody).post("booking");

        response2.prettyPrint();
        Assert.assertEquals(response2.getStatusCode(), 200, "Status code is not as expected");

        Assert.assertEquals(response2.jsonPath().getString("booking.firstname"), "Andras", "firstname is not as expected (Andras)");
        Assert.assertEquals(response2.jsonPath().get("booking.lastname"), "LeFou", "lastname is not as expected (Leclo)");
        Assert.assertEquals(response2.jsonPath().getInt("booking.totalprice"), 128, "Price is not as expected (120)");
        Assert.assertEquals(response2.jsonPath().getBoolean("booking.depositpaid"), true, "Paid deposit should be true but it is not");
        Assert.assertEquals(response2.jsonPath().get("booking.bookingdates.checkin"), "2022-01-05", "checkin date is not as expected");
        Assert.assertEquals(response2.jsonPath().get("booking.bookingdates.checkout"), "2022-07-12", "checkout date is not as expected");
        Assert.assertEquals(response2.jsonPath().get("booking.additionalneeds"), "Rockwell B-1 Lancer", "additional needs are not as expected");

    }
}
