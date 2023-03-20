import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PositiveApiTests {

    @Test
    @DisplayName("Get list of users and receive response with status code 200")
    public void getListOfUsersInJson() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(not(emptyOrNullString()));
    }

    @Test
    @DisplayName("Post new user and receive response with status code 201")
    public void postNewUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Jason");
        requestBody.put("job", "actor");
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body(not(emptyOrNullString()))
                .body("name", equalTo("Jason"),
                        "job", equalTo("actor"));
    }
}
