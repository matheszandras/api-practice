package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdTest extends BaseTest {
    @Test
    public void GetBookingIdsWithoutFilterTest() {

        Response response = RestAssured.given(spec).get("booking/");
        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it isn't");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");

        Assert.assertFalse(bookingIds.isEmpty(), "List of bookingIds is empty");

    }
    @Test
    public void GetBookingIdsWithFilter() {
        spec.queryParams("firstname","Sally");
        spec.queryParams("lastname","Brown");
        Response response = RestAssured.given(spec).get("booking/");
        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it isn't");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");

        Assert.assertFalse(bookingIds.isEmpty(), "List of bookingIds is empty");
    }
}
