package com.encode;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetHotelsListNegativeTest {

    private static final String FAKE_UUID = "ac888dc5-d193-4700-FAKE-UUID";
    private static final String TRUE_UUID_GET_LIST = "ac888dc5-d193-4700-b12c-abb43e289301";
    private static final String TRUE_UUID_GET_HOTEL_INFO = "a9446b47-b0a1-454d-b89d-c3b848b7dc7c";
    private static final String BASE_URI = "https://run.mocky.io/v3/";

    @BeforeTest
    void setup() {
        baseURI = BASE_URI;
    }

    @Test
    void getHotelsListFakeUuid() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(FAKE_UUID)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(404);
    }

    @Test
    void getHotelsListFakeMethod() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(TRUE_UUID_GET_LIST)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(404);
    }

    @Test
    void getHotelsInfoFakeUuid() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(FAKE_UUID)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(404);
    }

    @Test
    void getHotelsInfoFakeMethod() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(TRUE_UUID_GET_HOTEL_INFO)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(404);

    }
}


