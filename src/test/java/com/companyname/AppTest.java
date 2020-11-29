package com.companyname;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.exception.XmlPathException;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
    	System.out.println("Hello World");
    	//RestAssured.baseURI="https://reqres.in/api/users?page=2";
    	RestAssured.baseURI="https://reqres.in";
    	RestAssured.basePath="/api/users";
    	RestAssured.rootPath = "data";
    	RequestSpecification reqspec = RestAssured.given();
    	reqspec.headers("content-type","application/json");
    	reqspec.queryParams("page", 2, "id", 8);
    	reqspec.get().then().statusCode(200);
    	reqspec.get().then().assertThat().body("last_name", equalToIgnoringCase("Ferguson"),
    			"id", hasToString("8"));
    	//reqspec.get().then().body("id", hasToString("9"));
    	reqspec.get().then().log().all();
    	//reqspec.get().then().body("data.id", hasItems(7,8));
    	String respInString = reqspec.get().body().asString();
    	System.out.println(respInString);
    	JsonPath respInJson=reqspec.get().body().jsonPath();
    	//XmlPath respInXml= new XmlPath(respInString);
    	//System.out.println(respInXml);
    	//System.out.println(respInJson);
    	//System.out.println(respInXml.get("data.first_name"));
    	System.out.println(respInJson.get("data.first_name") + "\n" + respInJson.get("ad.text"));
		/*
		 * String str = "Hello"; String str1 = ""; for(int i = str.length() - 1; i >=0 ;
		 * i--) { str1 = str1 + str.charAt(i); } System.out.println(str1);
		 * assertEquals("olleH",str1);
		 */
    }
    
    @Test
    public void shouldAnswerWithTrue1()
    {
    	System.out.println("Hello World");
    	RestAssured.baseURI="https://reqres.in";
    	RestAssured.basePath="/api/users?";
    	given().queryParam("page", 2).when().get().then().contentType("application/json");
    	Response resp = given().queryParam("page", 2).when().get().then().extract().response();
    	//RestAssured.get().then().body("id", hasItems(7,8));
    	String response = resp.asString();
    	System.out.println(response);
    	JsonPath respinjson = new JsonPath(response);
    	XmlPath respInXml= new XmlPath(response);
    	System.out.println(respInXml);
    	System.out.println(respinjson);
    	//System.out.println(respInXml.get("data.first_name"));
    	System.out.println(respinjson.get("data[1].first_name") + "\n" + respinjson.get("ad.text"));
    }

	
}
