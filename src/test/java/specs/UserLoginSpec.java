package specs;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static helpers.CustomerAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;


public class UserLoginSpec {
        public static RequestSpecification loginRequestSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .contentType(JSON)
                .baseUri("https://reqres.in")
                .basePath("/api");

        public static ResponseSpecification createdResponse = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .log(STATUS)
                .log(BODY)
                .build();

        public static ResponseSpecification successfulResponse = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(STATUS)
                .log(BODY)
                .build();

        public static ResponseSpecification noContentResponse = new ResponseSpecBuilder()
                .expectStatusCode(204)
                .log(STATUS)
                .build();

        public static ResponseSpecification notFoundResponse = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .log(STATUS)
                .build();
    }