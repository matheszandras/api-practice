package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckTest extends BaseTest {
    @Test
    public void HealthCheckTest() {
        given().spec(spec).when().get("ping")
                .then().assertThat().statusCode(201);

    }
    @Test
    public void HeadersAndCookiesTest() {
        Header someHeader = new Header("some name","some value");
        Cookie someCookie = new Cookie.Builder("some cookie","some cookie value").build();

        Response response = RestAssured.given(spec).
                cookie(someCookie).
                header(someHeader).
                cookie("testcookiename","testcookievalue").
                header("testheader","testheadervalue").
                log().all().
                get("ping");

        Headers headers = response.getHeaders();
        System.out.println(headers);

        Header serverHeader1 = headers.get("Server");
        System.out.println(serverHeader1 + "blablasfsőoufgao");

        String serverHeader2 = response.getHeader("Server");
        System.out.println("Server:" + serverHeader2);

        System.out.println("********");

        Cookies cookies = response.getDetailedCookies();
        System.out.println(cookies);

        given().spec(spec).when().get("ping")
                .then().assertThat().statusCode(201);
    }
}
