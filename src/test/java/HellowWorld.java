import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @Test
    public void helloWorld6() {
        String login = "super_admin";
        List<String> passwords = Arrays.asList(
                "123456", "password", "12345678", "qwerty", "123456789",
                "12345", "1234", "111111", "1234567", "dragon",
                "123123", "baseball", "abc123", "football", "monkey",
                "letmein", "696969", "shadow", "master", "666666",
                "qwertyuiop", "123321", "mustang", "1234567890", "michael"
        );

        for (String password : passwords) {
            String authCookie = getAuthCookie(login, password);
            if (authCookie != null) {
                String authResult = checkAuthCookie(authCookie);
                if (authResult.equals("You are authorized")) {
                    System.out.println("Login: " + login);
                    System.out.println("Password: " + password);
                    System.out.println("Authentication result: " + authResult);
                    System.out.println("Правильный пароль: " + password);
                    break;
                }
            }
        }
    }

    public static String getAuthCookie(String login, String password) {
        Response response = RestAssured
                .given()
                .formParam("login", login)
                .formParam("password", password)
                .when()
                .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                .andReturn();
        return response.getCookie("auth_cookie");
    }

    public static String checkAuthCookie(String authCookie) {
        Response response = RestAssured
                .given()
                .cookie("auth_cookie", authCookie)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                .andReturn();
        return response.getBody().asString();


    }


    @Test
    public void checkPassword() {
        String login = "super_admin";
        List<String> passwords = Arrays.asList(
                "123456", "password", "12345678", "qwerty", "123456789",
                "12345", "1234", "111111", "1234567", "dragon",
                "123123", "baseball", "abc123", "football", "monkey",
                "letmein", "696969", "shadow", "master", "666666",
                "qwertyuiop", "123321", "mustang", "1234567890", "michael"
        );

        for (String password : passwords) {
            String authCookie = getAuthCookie1(login, password);
            if (authCookie != null) {
                String authResult = checkAuthCookie2(authCookie);
                if (authResult.equals("You are authorized")) {
                    System.out.println("Правильный пароль: " + password);
                    break;
                }else {
                    System.out.println(" Вам не повезло. ");
                    System.out.println(" Пароль не правильный - " + password);
                }
            }
        }
    }

    public static String getAuthCookie1(String login, String password) {
        Response response = RestAssured
                .given()
                .formParam("login", login)
                .formParam("password", password)
                .when()
                .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                .andReturn();

        Headers mmm = response.getHeaders();
        System.out.println("\nЗаголовки - \n" + mmm);

        return response.getCookie("auth_cookie");
    }

    public static String checkAuthCookie2(String authCookie) {
        Response response = RestAssured
                .given()
                .cookie("auth_cookie", authCookie)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                .andReturn();

        Headers mmm = response.getHeaders();
        System.out.println("\nЗаголовки - \n" + mmm);

        return response.getBody().asString();

    }

    @Test
    public void testLongRedirect() {
        RestAssured.baseURI = "https://playground.learnqa.ru/api";

        String currentUrl = "/long_redirect";
        int redirectCount = 0;

        while (true) {
            Response response = given().get(currentUrl);
            redirectCount++;

            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                System.out.println("Достигнут последний УРЛ: " + currentUrl);
                break;
            }

            currentUrl = response.getHeader("Location");
            System.out.println("Ридирект на...: " + currentUrl);
        }

        System.out.println("Количество ридеректов: " + redirectCount);
    }


    @Test
    public void testCountRedirects() {
        String url = "https://playground.learnqa.ru/api/long_redirect";
        int redurects = 0;
        while (true) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(url)
                    .andReturn();
            redurects++;
            int statusCode = response.getStatusCode();
            if (statusCode == 301 || statusCode == 302 || statusCode == 303) {
                url = response.getHeader("Location");
            } else if (statusCode == 200) {
                System.out.println("Количество редиректов: " + redurects);
                break;
            }
        }
    }
}






