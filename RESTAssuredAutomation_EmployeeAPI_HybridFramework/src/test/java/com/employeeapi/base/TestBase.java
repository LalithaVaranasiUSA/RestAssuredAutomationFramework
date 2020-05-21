package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="19";
	
	public Logger logger;
	
	public TestBase()
	{
		logger=Logger.getLogger("EmploeesRestAPI");
		PropertyConfigurator.configure("C:\\myJavaPrograms\\RESTAssuredAutomation_EmployeeAPI_HybridFramework\\src\\main\\resources\\log4j.properties");
		logger.setLevel(Level.DEBUG);
	}	
}
