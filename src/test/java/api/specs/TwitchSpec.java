package api.specs;

import common.config.ApiConfig;
import common.config.AuthConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static common.helpers.AllureListener.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class TwitchSpec {
    static final ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
    public static final RequestSpecification twitchAuthRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all()
            .baseUri(apiConfig.authBaseURI())
            .basePath(apiConfig.authBasePath());

    static final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
    public static final RequestSpecification twitchRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all()
            .baseUri(apiConfig.baseURI())
            .basePath(apiConfig.basePath())
            .header("Authorization", "Bearer " + authConfig.userToken())
            .header("Client-id", authConfig.userClientId());

    public static final ResponseSpecification badRequestResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();

    public static final ResponseSpecification OkResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

    public static final ResponseSpecification NoContentResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(204)
            .build();

}
