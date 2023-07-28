package com.encode;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class GetHotelsListPositiveTest {

    private static final String BASE_URI = "https://run.mocky.io/v3/";
    private static final String KEY_UUID = "uuid";
    private static final String KEY_NAME = "name";
    private static final String KEY_POSTER = "poster";
    private static final String KEY_PHOTOS = "photos";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PRICE = "price";
    private static final String KEY_RATING = "rating";
    private static final String KEY_SERVICES = "services";
    private static final String KEY_FREE = "free";
    private static final String KEY_PAID = "paid";
    private static final String TRUE_UUID_GET_LIST = "ac888dc5-d193-4700-b12c-abb43e289301";

    @BeforeTest
    public static void setup() {
        baseURI = BASE_URI;
    }

    @Test
    public void getHotelsList() {
        Response response = getResponse();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(response.getBody().asString().contains(KEY_UUID));
        Assert.assertTrue(response.getBody().asString().contains(KEY_NAME));
        Assert.assertTrue(response.getBody().asString().contains(KEY_POSTER));

    }

    @Test
    public void getHotelInfo() {
        Response response = getResponse();
        String uuid = (String) response.jsonPath().getList(KEY_UUID).get(0);

        Response responseInfo = given()
                .contentType(ContentType.JSON)
                .when()
                .get(uuid)
                .then()
                .log()
                .all()
                .assertThat()
                .extract().response();

        List<Object> photos = responseInfo.jsonPath().getList(KEY_PHOTOS);

        List<Integer> integerList = getIntegers();

        boolean contains = integerList.contains(photos.size());


        Assert.assertEquals(200, responseInfo.statusCode());
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_UUID));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_NAME));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_POSTER));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_ADDRESS));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_PRICE));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_RATING));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_SERVICES));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_PHOTOS));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_FREE));
        Assert.assertTrue(responseInfo.getBody().asString().contains(KEY_PAID));
        Assert.assertTrue(contains);


    }

    private List<Integer> getIntegers() {
        List<Integer> integerList = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            integerList.add(i);
        }
        return integerList;
    }

    private Response getResponse() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(TRUE_UUID_GET_LIST)
                .then()
                .log()
                .all()
                .assertThat()
                .extract().response();
    }
}

