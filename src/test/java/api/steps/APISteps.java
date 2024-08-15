package api.steps;

import api.models.AuthRequestDTO;
import api.models.ScheduleRequestDTO;
import api.models.StreamsResponseDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static api.specs.TwitchSpec.*;
import static io.restassured.RestAssured.given;

public class APISteps {

    @Step("Get auth token")
    public static ValidatableResponse getAuthResponse(AuthRequestDTO requestBody) {
        return given(twitchAuthRequestSpec)
                .body(requestBody)
                .when()
                .post()
                .then();
    }

    @Step("Create new schedule")
    public static ValidatableResponse createBroadCastSchedule(ScheduleRequestDTO requestBody, int broadcasterId) {
        return given(twitchRequestSpec)
                .queryParam("broadcaster_id", broadcasterId)
                .body(requestBody)
                .when()
                .post("/schedule/segment")
                .then();
    }

    @Step("Delete created schedule")
    public static String deleteBroadCastSchedule(int broadcasterId, String scheduleId) {
        return given(twitchRequestSpec)
                .queryParam("broadcaster_id", broadcasterId)
                .queryParam("id", scheduleId)
                .when()
                .delete("/schedule/segment")
                .then()
                .spec(NoContentResponseSpec)
                .extract().asString();
    }

    @Step("Get all streams by sending GET request")
    public static StreamsResponseDTO getAllStreams() {
        return given(twitchRequestSpec)
                .when()
                .get("/streams")
                .then()
                .spec(OkResponseSpec)
                .extract().as(StreamsResponseDTO.class);
    }

    @Step("Retrieve all streams with {0} streamLanguage via GET request")
    public static ValidatableResponse getStreamsWithSpecifiedParameter(String queryParam, String language) {
        return given(twitchRequestSpec)
                .queryParam(queryParam, language)
                .when()
                .get("/streams")
                .then();
    }

    @Step("Retrieve a user by id by sending GET request")
    public static ValidatableResponse getUserById(String id) {
        return given(twitchRequestSpec)
                .queryParam("id", id)
                .when()
                .get("/users")
                .then();
    }


    @Step("Retrieve a video by sending GET request")
    public static ValidatableResponse getSingleVideoById(String id) {
        return given(twitchRequestSpec)
                .queryParam("id", id)
                .when()
                .get("/videos")
                .then();
    }

    @Step("Retrieve list of videos by sending GET request")
    public static ValidatableResponse getListOfVideosByStreamerId(String userId) {
        return given(twitchRequestSpec)
                .queryParam("user_id", userId)
                .when()
                .get("/videos")
                .then();
    }
}
