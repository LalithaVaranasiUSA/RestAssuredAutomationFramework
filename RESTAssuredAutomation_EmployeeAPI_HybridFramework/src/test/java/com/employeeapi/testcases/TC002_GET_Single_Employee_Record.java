package com.employeeapi.testcases;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC002_GET_Single_Employee_Record extends TestBase {
	public TC002_GET_Single_Employee_Record()
	{
		super();
	}
	
	@BeforeClass
	public void getEmployeeData() throws InterruptedException
	{
	logger.info("*********************started TC002_GET_Single_Employee_Record***************");
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	httpRequest= RestAssured.given();
	response=httpRequest.request(Method.GET,"/employee/"+empID);
	Thread.sleep(5000);
	}
	
	//@Test
	void checkResponseBody()
	{
		
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains("success"), true);
	
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode=response.getStatusCode();
		logger.fatal("Call failed......");
		Assert.assertEquals(statusCode,400);
	}
	
	@Test
	void checkResponseTime()
	{
		long responseTime=response.getTime();
		logger.warn("Response time is tooo much");
		Assert.assertEquals(responseTime<4000,true);
	}
	
	@Test
	void checkServerType()
	{
		logger.info("*********************checking Server Type***************");
		String serverType=response.getHeader("Server");
		logger.info("Server type==========>>>>>>>"+serverType);
		Assert.assertEquals(serverType,"nginx/1.16.0");	
	}
	
	@Test
	public void checkContentLength()
	{
		logger.info("*********************checking Content Length***************");
		String contentLength=response.getHeader("Content-Length");
		logger.info("Content Length==========>>>>>>>"+contentLength);
		if(Integer.parseInt(contentLength)<50)
			logger.warn("content length is less than 50");
		Assert.assertTrue(Integer.parseInt(contentLength)>50);	
	}
	@AfterClass
	public void tearDown()
	{
		logger.info("*********************Finished TC002_GET_Single_Employee_Record***************");
	}
	

}
