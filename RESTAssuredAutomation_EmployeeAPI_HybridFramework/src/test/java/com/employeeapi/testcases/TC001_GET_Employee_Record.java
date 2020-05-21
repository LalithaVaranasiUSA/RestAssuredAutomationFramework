package com.employeeapi.testcases;

import org.testng.annotations.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_Employee_Record  extends TestBase {
	public TC001_GET_Employee_Record ()
	{
		super();
	}
	
	
	@BeforeClass
	public void getAllEmployees()
	{
	logger.info("*********************started TC001 get all employees***************");
	
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	httpRequest= RestAssured.given();
	response=httpRequest.request(Method.GET,"/employees");
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("*********************checking response body***************");
		String responseBody=response.getBody().asString();
		logger.info("Response Body===========>>>>>>>>"+responseBody);
		Assert.assertTrue(responseBody!=null);		
	}
	
	@Test
	void checkStatus()
	{
		logger.info("*********************checking status***************");
		int status=response.getStatusCode();
		logger.info("Status code===========>>>>>>>>"+status);
		Assert.assertEquals(status,200);		
	}
	@Test
	void checkResponseTime()
	{
		logger.info("*********************checking Response Time***************");
		long time=response.getTime();
		logger.info("Response Time===========>>>>>>>>"+time);
		if(time>2000)
			logger.warn("Response time is greater than 4000");
		Assert.assertTrue(time<4000);		
	}
	@Test
	void checkStatusLine()
	{
		logger.info("*********************checking StatusLine***************");
		String statusLine=response.getStatusLine();
		logger.info("Status LIne==========>>>>>>>"+statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");	
	}
	@Test
	void checkContentType()
	{
		logger.info("*********************checking ContentType***************");
		String contentType=response.getHeader("Content-Type");
		logger.info("Content type==========>>>>>>>"+contentType);
		Assert.assertEquals(contentType,"application/json;charset=utf-8");	
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
	void checkContentCoding()
	{
		logger.info("*********************checking Content Coding***************");
		String contentcoding=response.getHeader("Content-Encoding");
		logger.info("Content Encoding==========>>>>>>>"+contentcoding);
		Assert.assertEquals(contentcoding,"gzip");	
	}
	
	@Test
	public void checkContentLength()
	{
		logger.info("*********************checking Content Length***************");
		String contentLength=response.getHeader("Content-Length");
		logger.info("Content Length==========>>>>>>>"+contentLength);
		if(Integer.parseInt(contentLength)<100)
			logger.warn("content length is less than 100");
		Assert.assertTrue(Integer.parseInt(contentLength)>100);	
	}
	
	@Test
	void checkCookies()
	{
		logger.info("*********************checking Cookies***************");
		String cookie= response.getCookie("CookieID");
		System.out.println("************Cookie ID is*************"+cookie);
	}
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********************Finished TC001 get all employees***************");
	}
	
	
}

