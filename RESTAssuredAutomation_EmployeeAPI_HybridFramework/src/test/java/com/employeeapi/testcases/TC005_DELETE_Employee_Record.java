package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
//import com.employeeapi.utilities.RestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_DELETE_Employee_Record extends TestBase{
	public TC005_DELETE_Employee_Record()
	{
		super();
	}
	
	@BeforeClass
	public void deleteEmployee() throws InterruptedException
	{
	logger.info("*********************started TC005_DELETE_Single_Employee_Record***************");
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	httpRequest= RestAssured.given();
	response=httpRequest.request(Method.GET,"/employees");
	System.out.println(response.getBody().asString());
	
	
	
	JsonPath responseData=response.jsonPath();
	
	Thread.sleep(5000);
	String empID=responseData.get("data[20].id");
	response=httpRequest.request(Method.DELETE,"/delete/"+empID);
	Thread.sleep(5000);
	}

	@Test
	void checkResponseBody()
	{		
		String responseBody=response.getBody().asString();	
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
	}
	
	

}
