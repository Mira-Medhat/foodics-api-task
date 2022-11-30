package foodicstests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class APITests {
    public String token;
    private Response response;

    @Test

    public void testSuccessfulLogin() throws IOException {
        String excelPath = "./data/users.xlsx";
        String sheetName = "Sheet1";
        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
        JSONObject request = new JSONObject();
        request.put("username", excel.getCellData(1, 0));
        request.put("password", excel.getCellData(1, 1));
/*    JsonObject requestParams = new JsonObject();
                   requestParams.addProperty("username","omarfoodics2+test2@gmail.com");
                   requestParams.addProperty("password","sk190517225LM@$*");*/

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(request.toString())
                .when()
                .post("https://pay.foodics.dev/public-api/v1/App/Login")
                .then()
                .extract().response();
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();
        token = jsonPathEvaluator.getString("token").toString();


        System.out.println(token);

        System.out.println(body.asString());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);


    }

    @Test
    public void testSuccessfulGetMerchantData() {


        Response response = given()
                .headers("Content-type", "application/json", "Authorization", "Bearer " + token)
                .and()
                .when()
                .get("https://pay.foodics.dev/public-api/v1/App/GetMerchantInfo")
                .then()
                .extract().response();
        ResponseBody body = response.body();
        String bd = body.asString();
        System.out.println(bd);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
