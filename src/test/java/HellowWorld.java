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
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

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
        System.out.println("\nЗаголовки - \n" + mmm);

        String mmm1 = response.getHeader("Location");
        System.out.println("\nРедирект на  - " + mmm1);
    }

    @Test
    public void helloWorld4() throws InterruptedException {

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();
        String accessToken = response.jsonPath().getString("token");
        System.out.println("Токен доступа - " + accessToken);

        // Шаг 2: запрос с токеном до завершения задачи
        Response responseWithTokenBeforeCompletion = RestAssured
                .given()
                .queryParam("token", accessToken)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();
        String statusBefore = responseWithTokenBeforeCompletion.jsonPath().getString("status");
        System.out.println("Статус до завершения задачи - " + statusBefore);

        // Шаг 3: ожидание нужного количества секунд
       int secondsToWait = response.jsonPath().getInt("seconds");
        // Переводим секунды в миллисекунды
       Thread.sleep(secondsToWait * 1000);


        // Шаг 4: запрос с токеном после завершения задачи
        Response responseWithTokenAfterCompletion = RestAssured
                .given()
                .queryParam("token", accessToken)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();
        String statusAfter = responseWithTokenAfterCompletion.jsonPath().getString("status");
        System.out.println("Статус после завершения задачи - " + statusAfter);

        // Проверка наличия результата после завершения задачи
        String result = responseWithTokenAfterCompletion.jsonPath().getString("result");
        if (result != null) {
            System.out.println("Результат: " + result);
        } else {
            System.out.println("Результат еще не готов.");
        }
    }
}