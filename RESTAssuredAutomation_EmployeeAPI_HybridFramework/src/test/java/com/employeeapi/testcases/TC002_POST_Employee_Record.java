package com.employeeapi.testcases;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC002_POST_Employee_Record extends TestBase {

		public TC002_POST_Employee_Record()
		{
			super();
		}
		
		String empName=RestUtil.empName();
		String empSalary=RestUtil.empSalary();
		String empAge=RestUtil.empAge();
		
		@BeforeClass
		public void createEmployee() throws InterruptedException
		{
			logger.info("*************started TC003_POST_Employee_Record************");
			RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
			httpRequest=RestAssured.given();
			
			JSONObject requestParams=new JSONObject();
			requestParams.put("name",empName);
			requestParams.put("salary",empSalary);
			requestParams.put("age",empAge);
			 			
			httpRequest.header("Content-Type","application/json");
			httpRequest.body(requestParams.toJSONString());		
			response=httpRequest.request(Method.POST,"/create");
			Thread.sleep(5000);
		}
		
		@Test
		void checkResponseBody()
		{
			String responseBody=response.getBody().asString();
			System.out.println("Response Body is.........................."+responseBody);
			
			
			JsonPath data=response.jsonPath();
			String age=data.get("data.age");
			String salary=data.get("data.salary");
			String name=data.getString("data.name");
			Assert.assertEquals(responseBody.contains(name),true,"name is not same");
			Assert.assertEquals(responseBody.contains(age),true,"age is not same");
			Assert.assertEquals(responseBody.contains(salary),true,"salary is not same");
		}
		
		@Test
		void checkStatusCode()
		{
			int statusCode=response.getStatusCode();
			Assert.assertEquals(statusCode,200);
		}
//		@Test
		void checkStatusLine()
		{
			String statusLine=response.getStatusLine();
			Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		}
		
	//	@Test
		void checkResponseTime()
		{
			long responseTime=response.getTime();
			logger.warn("Response time is tooo much");
			Assert.assertEquals(responseTime<4000,true);
		}
		
		//@Test
		void checkServerType()
		{
			logger.info("*********************checking Server Type***************");
			String serverType=response.getHeader("Server");
			logger.info("Server type==========>>>>>>>"+serverType);
			Assert.assertEquals(serverType,"nginx/1.16.0");	
		}
		
		//@Test
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