package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.herokuapp.restfulbooker.CreateBooking.createBooking;

public class UpdateBookingTest {

    @Test
    public void UpdateBookingTest() {

        Response responseCreate = createBooking();
        responseCreate.prettyPrint();
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        JSONObject body = new JSONObject();
        body.put("firstname", "Jacques");
        body.put("lastname", "LeClo");
        body.put("totalprice", 1800);
        body.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2022-01-04");
        bookingDates.put("checkout", "2030-12-31");
        body.put("bookingdates", bookingDates);

        body.put("additionalneeds", "uranium, 41st field artillery regiment");

        Response responseUpdate = RestAssured.given().auth().preemptive().basic("admin","password123").contentType(ContentType.JSON).
                body(body.toString()).put("http://localhost:3001/booking/" + bookingId);
        responseUpdate.prettyPrint();

        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Status code is not as expected");

        Assert.assertEquals(responseUpdate.jsonPath().getString("firstname"), "Jacques", "firstname is not as expected (Andras)");
        Assert.assertEquals(responseUpdate.jsonPath().get("lastname"), "LeClo", "lastname is not as expected (Leclo)");
        Assert.assertEquals(responseUpdate.jsonPath().getInt("totalprice"), 1800, "Price is not as expected (120)");
        Assert.assertEquals(responseUpdate.jsonPath().getBoolean("depositpaid"), true, "Paid deposit should be true but it is not");
        Assert.assertEquals(responseUpdate.jsonPath().get("bookingdates.checkin"), "2022-01-04", "checkin date is not as expected");
        Assert.assertEquals(responseUpdate.jsonPath().get("bookingdates.checkout"), "2030-12-31", "checkout date is not as expected");
        Assert.assertEquals(responseUpdate.jsonPath().get("additionalneeds"), "uranium, 41st field artillery regiment", "additional needs are not as expected");
    }

}
