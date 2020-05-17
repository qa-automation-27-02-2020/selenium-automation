package io.react.realworld.api;

import com.hillel.auto.model.ApiUser;
import com.hillel.auto.model.User;
import com.hillel.auto.utils.UserData;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

/**
 * Created by alpa on 5/17/20
 */
public class RegistrationTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://conduit.productionready.io";
        RestAssured.basePath = "/api";
        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setAccept(ContentType.JSON)
                        .setContentType(ContentType.JSON)
                        .log(LogDetail.ALL)
                        .build();
    }



    @Test
    public void registrationUserTest() {
        User user = UserData.randomUser();
        ApiUser apiUser = new ApiUser();
        apiUser.setUser(user);

        User newUser = RestAssured
                .given()
                    .body(apiUser)
                .when()
                    .post("/users")
                .then()
                    .statusCode(200)
                    .extract().body()
                    .as(ApiUser.class)
                    .getUser();

        assertThat(newUser.getEmail()).isEqualToIgnoringCase(user.getEmail());
        assertThat(newUser.getUsername()).isEqualToIgnoringCase(user.getUsername());
    }
}
