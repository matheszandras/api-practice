package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setup() {
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();
    }

    protected Response createBooking() {
        JSONObject body = new JSONObject();
        body.put("firstname", "Andras");
        body.put("lastname", "LeClo");
        body.put("totalprice", 120);
        body.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2022-01-04");
        bookingDates.put("checkout", "2030-12-31");
        body.put("bookingdates", bookingDates);

        body.put("additionalneeds", "uranium, 41st field artillery regiment");

        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("booking");
        return response;
    }
}
