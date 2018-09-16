package dpcucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import cucumber.api.java.en.Then;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.Properties;


public class Stepdefs {
    Properties prop = new Properties();
	private Response create_issue_res;
    
	@Given("^a user want to create a issue$")
	public void a_user_want_to_create_a_issue() throws Exception {
		FileInputStream files = new FileInputStream("src\\test\\java\\config\\env.properties");
		prop.load(files);
	}
	
	@When("^a request is sent$")
	public void a_request_is_sent() throws Exception {
		RestAssured.baseURI= prop.getProperty("JiraHost");
		this.create_issue_res = given().header("Content-Type","application/json").
				cookie("JSESSIONID", getSessionId()).
				body(Payload.getCreateIssue()).
				when().post("/rest/api/2/issue");
	}
	
	@Then("^a issue is created in jira$")
	public void a_issue_is_created_in_jira() throws Exception {
		create_issue_res.then().assertThat(). // status 201 created
		statusCode(200);
	    
		
	}
	
	private String getSessionId()
	{
		RestAssured.baseURI= prop.getProperty("JiraHost");
		Response res = given().header("Content-Type","application/json").
		body("{ \"username\": \""+
				prop.getProperty("USERNAME")+
				"\", \"password\": \""+
				prop.getProperty("PASSWORD") +"\" }").
		when().
		post("/rest/auth/1/session").then().statusCode(200).
		extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		String session_id = js.get("session.value"); 
		return session_id;
	}
	

}
