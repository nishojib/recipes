package nishojib;

import static io.restassured. RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response. Response;
import io.restassured.specification.RequestSpecification;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITests {
    
    @Test
    void readOneRecipe()    {
        
        String[] testData = {"1100992",
                            "French Fries"};

        Response response = get ("http://0.0.0.0:4567/recipes/" + testData[0]);

        if (!response.asString().contains(testData[1]))
            Assert.fail("Error querying /recipe/{id} with Data: id = " + testData[0]);
        System.out.println("Test readOneRecipe(): Successfully queried /recipes/{id} with id = " + testData[0]);
    }

    @Test
    void readOneIngredient()    {
        
        String[] testData = {"1001",
                            "butter"};

        Response response = get ("http://0.0.0.0:4567/ingredients/" + testData[0]);

        if (!response.asString().contains(testData[1]))
            Assert.fail("Error querying /ingredient/{id} with Data: id = " + testData[0]);
        
        System.out.println("Test readOneIngredient(): Successfully queried /ingredient/{id} with id = " + testData[0]);
    }

    @Test
    void createAndDeleteIngredient()    {

        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "testBanana");
        requestParams.put("amount", 10);
        requestParams.put("unit", "testPiece");
        requestParams.put("original", "test Long String Bananananana");

        request.body(requestParams.toJSONString());

        Response response = request.post("http://0.0.0.0:4567/ingredients");

        String responseStr = response.asString();
        if (!responseStr.contains("SUCCESS"))
            Assert.fail("Error creating /ingredient");

        responseStr = responseStr.substring(31, responseStr.length() - 2);
        int createdId = Integer.parseInt(responseStr);

        System.out.println("Test createAndDeleteIngredient(): Successfully created /ingredient with id = " + createdId);

        Response deleteResponse = request.delete("http://0.0.0.0:4567/ingredients/" + createdId);
        if (!deleteResponse.asString().contains("SUCCESS"))
            Assert.fail("Error deleting /ingredient/{id} with Data: id = " + createdId);

        System.out.println("Test createAndDeleteIngredient(): Successfully deleted /ingredient/{id} with id = " + createdId);
    }

}
