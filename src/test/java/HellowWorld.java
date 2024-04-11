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

    @Test
    public void helloWorld4() {

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

        Response response1 = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get(mmm1)
                .andReturn();
        response1.prettyPrint();

        Headers mmm2 = response1.getHeaders();
        System.out.println("Заголовки - " + mmm2);

        String mmm3 = response1.getHeader("Location");
        System.out.println("Редирект на  - " + mmm3);


        Response response2 = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get(mmm3)
                .andReturn();
        response2.prettyPrint();

        Headers mmm4 = response2.getHeaders();
        System.out.println("Заголовки - " + mmm4);

        String mmm5 = response2.getHeader("Location");
        System.out.println("Редирект на  - " + mmm5);
    }

}