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
import java.util.ArrayList;
import java.util.Properties;


public class Stepdefs {
    Properties prop = new Properties();
<<<<<<< HEAD
    private Response create_res;
    private Response all_res;
    private Response delete_res;
    private  String id;


    @Given("^a user want to get all bookings$")
    public void a_user_want_to_get_all_bookings() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        FileInputStream files = new FileInputStream("src\\test\\java\\config\\env.properties");
        prop.load(files);
    }
    @When("^a request is sent$")
    public void a_request_is_sent() throws Exception {
        RestAssured.baseURI= prop.getProperty("BookingsHost");
        this.all_res = given().
                when().get("/bookings/all");
    }
    @Then("^all bookings should be returned$")
    public void all_bookings_should_be_returned() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(200,all_res.statusCode());
    }

    @Given("^a booking record$")
    public void a_booking_record() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        FileInputStream files = new FileInputStream("src\\test\\java\\config\\env.properties");
        prop.load(files);
    }

    @When("^request to create a new booking$")
    public void request_to_create_a_new_booking() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI= prop.getProperty("BookingsHost");
        this.create_res = given().header("Content-Type","application/json").
                body(Payload.getCreateBooking()).
                when().post("/bookings/create");
    }

    @Then("^a new booking should be created$")
    public void a_new_booking_should_be_created() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        String res = this.create_res.asString();

        JsonPath js = new JsonPath(res);
        int count = js.getInt("size");
        System.out.println("******************************");
//        System.out.println(res);
        //get last id
        /*JsonPath.read(jsonString, sourceFieldFormula);*/
//       this.id = com.jayway.jsonpath.JsonPath.read(res,"$.[(@.length-1)].id");
        ArrayList<String> ids = js.get("id");
        System.out.println(ids);
        System.out.println(ids.size());
        ids.get(ids.size()-1);

        System.out.println(String.valueOf(ids.get(ids.size()-1)));
//        System.out.println(ids.get(ids.size()-1).toString());
        System.out.println("=====after  ids.get(ids.size()-1);=========");

        this.id = String.valueOf(ids.get(ids.size()-1));

        System.out.println(this.id);

        assertEquals(200,create_res.statusCode());
    }

    @Given("^a booing id$")
    public void a_booing_id() throws Exception {
        // Write code here that turns the phrase above into concrete actions

        FileInputStream files = new FileInputStream("src\\test\\java\\config\\env.properties");
        prop.load(files);

    }

    @When("^request to delete this record$")
    public void request_to_delete_this_record() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        String id = "3";
        RestAssured.baseURI= prop.getProperty("BookingsHost");
        this.delete_res = given().pathParam("id",id).
                when().get("/bookings/delete/{id}");
    }

    @Then("^this booking record should be deleted$")
    public void this_booking_record_should_be_deleted() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(200,delete_res.statusCode());
    }
=======
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
		statusCode(201);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
	        System.out.println(create_issue_res.statusCode());
	        assertEquals(create_issue_res.statusCode(),201);
		
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
		post("/rest/auth/1/session").then().statusCode(200). //200 means success
		extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		String session_id = js.get("session.value"); 
		return session_id;
	}
	
>>>>>>> origin/master

}
