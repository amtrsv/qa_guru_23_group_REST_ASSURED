import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTests {
//    Разработайте 5 автотестов на запросы из https://reqres.in/
//    Дайте код на ревью коллегам из вашего потока.
//    После ревью они должны поставить вашему репозиторию звездочку.
//    К сдаче принимается дз с минимум 2-мя звездами.

String URL = "https://reqres.in";
    @Test
    @DisplayName("Получение 1-го из пользователей")
    public void getSingleUserTest(){
        given()
                .log().uri()
                .baseUri(URL)
                .get("/api/users/2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("data.last_name", is("Weaver"))
                .body("support.url", is("https://reqres.in/#support-heading"));

    }
    @Test
    @DisplayName("Создание нового пользователя")
    public void postCreateNewUserTest(){
    File jsonFile = new File("src/test/resources/example.json");
        given()
                .log().uri()
                .baseUri(URL)
                .contentType(JSON)
                .body(jsonFile)
                .when()
                .log().all()
                .post("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(201)
                .body("name", is("Andrey"))
                .body("job", is("Qa"));

    }
    @Test
    @DisplayName("Удаление пользователя")
    public void deleteUserTest(){
        given()
                .log().uri()
                .baseUri(URL)
                .when()
                .log().all()
                .delete("/api/users/2")
                .then()
                .log().status()
                .assertThat()
                .statusCode(204);
    }

    @Test
    @DisplayName("Обновление данных пользователя")
    public void updateUserTest(){
        File jsonFile2 = new File("src/test/resources/example2.json");
        given()
                .log().uri()
                .baseUri(URL)
                .contentType(JSON)
                .body(jsonFile2)
                .when()
                .log().all()
                .put("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(200)
                .body("name", is("Andrey Matrosov"));
    }
    @Test
    @DisplayName("Поиск несуществующего пользователя")
    public void getUnknownUserTest(){
        given()
                .log().uri()
                .baseUri(URL)
                .when()
                .log().all()
                .get("/api/users")
                .then()
                .log().status()
                .assertThat()
                .statusCode(200);
    }
}
