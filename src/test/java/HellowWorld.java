import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class HellowWorld {

    @Test
    public void helloWorld() {
        Response response = get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void helloWorld2() {
        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        response.prettyPrint();

        String timestamp1 = response.get("messages[2].timestamp");
        System.out.println("Timestamp: " + timestamp1);

        List<Map<String, String>> messages = response.getList("messages");
        for (Map<String, String> message : messages) {
            String timestamp = message.get("timestamp");
            System.out.println("Timestamp: " + timestamp);
        }
    }

    @Test
    public void helloWorld3() {

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();
        response.prettyPrint();

        Headers mmm = response.getHeaders();
        System.out.println("Заголовки - " + mmm);

        String mmm1 = response.getHeader("Location");
        System.out.println("Редирект на  - " + mmm1);
    }

}