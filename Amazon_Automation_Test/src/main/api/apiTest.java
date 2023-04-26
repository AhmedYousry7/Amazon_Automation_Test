import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class apiTest {

    @Test(priority = 2)
    public void validateStatusCode(){

         given()
                .get("https://pay2.foodics.dev/cp_internal/whoami")
                .then().assertThat().statusCode(200).log().status().extract();
    }

    @Test(priority = 1)
    public void login() {

        Map<String, Object> loginAcc = new HashMap<>();
        loginAcc.put("email", "merchant@foodics.com");
        loginAcc.put("password", "123456");

        given()
                .log()
                .all()
                .body(loginAcc)
                .when()
                .contentType("application/json")
                .post("https://pay2.foodics.dev/cp_internal/login")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

    }
    @Test (priority = 3)
    public void getData() {
        RequestSpecification httpRequest = RestAssured.given();
        Response res = httpRequest.get("https://pay2.foodics.dev/cp_internal/whoami");
        ResponseBody body = res.body();
        //Converting the response body to string
        String foodics = body.asString();
        System.out.println("Data from the GET API- "+foodics);
    }
}
