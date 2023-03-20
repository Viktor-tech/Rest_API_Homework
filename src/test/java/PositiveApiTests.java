import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserList {
    @Test
    @DisplayName("Get list of users")
    public void getListOfUsersInJson() {
        given()
                .log().all()
                .accept(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(not(emptyOrNullString()));
    }

    @Test
    @DisplayName("Get list of users / negative test / Try POST, PUT, DELETE")
    public void tryPostPutDelete() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Jason");
        requestBody.put("job", "actor");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .delete("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body(not(emptyOrNullString()));
    }

}
