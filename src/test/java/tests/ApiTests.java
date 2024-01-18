package tests;
import models.UserBodyModel;
import models.UserGetResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static specs.UserLoginSpec.*;

public class ApiTests {

    @Test
    @Tag("Api")
    @DisplayName("Получение 1-го из пользователей")
    public void getSingleUserTest(){
        UserGetResponseModel response = step("Вызов сервиса", () -> given()
                .spec(loginRequestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(successfulResponse)
                .extract().response().as(UserGetResponseModel.class));

        step("Проверка ответа", () -> {
                assertEquals(2, response.getData().getId());
                assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
                assertEquals("Janet", response.getData().getFirstName());
                assertEquals("Weaver", response.getData().getLastName());
                assertEquals("https://reqres.in/img/faces/2-image.jpg", response.getData().getAvatar());
        });
    }
    @Test
    @Tag("Api")
    @DisplayName("Создание нового пользователя")
    public void postCreateNewUserTest() {
                UserBodyModel userBodyModel = new UserBodyModel();
                userBodyModel.setName("Andrey");
                userBodyModel.setJob("QA");
                UserBodyModel response = step("Вызов сервиса", () -> given()
                .spec(loginRequestSpec)
                .when()
                .body(userBodyModel)
                .post("/users")
                .then()
                .spec(createdResponse)
                .extract().response().as(UserBodyModel.class));

        step("Проверка ответа", () -> {
                assertEquals("Andrey", response.getName());
                assertEquals("QA", response.getJob());
        });
    }

    @Test
    @Tag("Api")
    @DisplayName("Удаление пользователя")
    public void deleteUserTest(){
        step("Вызов сервиса для получения списка", () -> given()
                .spec(loginRequestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(noContentResponse));
    }

    @Test
    @Tag("Api")
    @DisplayName("Обновление данных пользователя")
    public void updateUserTest() {
                UserBodyModel userBodyModel = new UserBodyModel();
                userBodyModel.setName("Ivan");
                userBodyModel.setJob("QA-LEAD");
                UserBodyModel response = step("Вызов сервиса", () -> given()
                .spec(loginRequestSpec)
                .when()
                .body(userBodyModel)
                .put("/users/2")
                .then()
                .spec(successfulResponse)
                .extract().response().as(UserBodyModel.class));


        step("Проверка ответа", () -> {
                assertEquals("Ivan", response.getName());
                assertEquals("QA-LEAD", response.getJob());

        });
    }

        @Test
        @Tag("Api")
        @DisplayName("Поиск несуществующего пользователя")
        public void getUnknownUserTest() {
            UserGetResponseModel response = step("Вызов сервиса для получения списка", () -> given()
                    .spec(loginRequestSpec)
                    .when()
                    .get("/users/23")
                    .then()
                    .spec(notFoundResponse)
                    .extract().response().as(UserGetResponseModel.class));

            step("Проверка ответа", () -> {
                assertNull(response.getData());
                assertNull(response.getSupport());
            });
        }
    }