import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NegativeApiTest {

    @Test
    @DisplayName("Get not existing user by Id and receive response with status code 404")
    public void getNotExistingUser() {
        given()
                .when()
                .pathParam("id", "23")
                .get("https://reqres.in/api/users/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .contentType(ContentType.JSON)
                .body(not(emptyOrNullString()));
    }

    @Test
    @DisplayName("Login without password and receive response with status code 400")
    public void deleteNotExistingUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "Jason@mail.com");
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body(not(emptyOrNullString()))
                .body("error", equalTo("Missing password"));
    }
}
