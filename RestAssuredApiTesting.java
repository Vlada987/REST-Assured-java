package rest;

import static io.restassured.matcher.RestAssuredMatchers.*; 
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static io.restassured.response.Response.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;


public class RestAssuredApiTesting {

	 

 public void GET() {
	    
	 baseURI = "https://simple-books-api.glitch.me";
	 when().get("/books"). 
	 then().statusCode(200)
	 .body("name[4]", equalTo("Untamed"))
	 .body("id[2]", equalTo(3));
   }

 public void GETandFindByJsonPath() {
	        
	 Response re = given().contentType(ContentType.JSON).
	 get("https://simple-books-api.glitch.me/books");
	    	
	 String name = re.jsonPath().get("name[0]").toString();
	      
   }

 public void test_POST() {
	    	
	JSONObject j1 = new JSONObject();
    baseURI = "https://reqres.in/";
    j1.put("name", "john");
	j1.put("job", "pilot");
	    	
	given().
	   header("Content-Type","application/json").
	   contentType(ContentType.JSON).
	   accept(ContentType.JSON).body(j1.toJSONString()).when().
	   post("/api/users").
	   then().statusCode(201);   
	    
   }


  public void test_PUT() {
	    	
	JSONObject jo = new JSONObject();
	baseURI = "https://reqres.in/";
	jo.put("name", "alex");
    jo.put("job", "developer");
    
	 given().
	    contentType("application/json").
	    body(jo.toJSONString()).
	    when().put("/api/users/2"). 
	    then().statusCode(200).body("name", equalTo("zef"));
	    
	     
	}

	 public void GetTokenAndPost() {
	        
	    JSONObject joo = new JSONObject();
	    joo.put("clientName", "Dzej");
	    joo.put("clientEmail", "bazzasszlntin@example.com");
	    
	    Response roo = (Response) given().contentType(ContentType.JSON).
	    		accept(ContentType.JSON). 
	    		body(joo.toJSONString())
	    		.post("https://simple-books-api.glitch.me/api-clients/");
	    
	    String token = roo.jsonPath().get("accessToken").toString();
	    
	    JSONObject jo1 = new JSONObject();
	    jo1.put("bookId", 1);
	    jo1.put("customerName","john");
	   given().
	     header("Authorization", "Bearer " + token). 
	     contentType(ContentType.JSON).
		 accept(ContentType.JSON).
		 body(jo1.toJSONString()).
		 when().post("https://simple-books-api.glitch.me/orders"). 
		 then().statusCode(201);
	   
	   
	    }


	
}
