package api.tests;

import io.qameta.allure.Step;
import api.models.*;

import static io.restassured.RestAssured.given;
import static api.specs.TwitchSpec.*;

public class APISteps {

    @Step("Get auth token")
    public static AuthResponseDTO getAuthResponse(AuthRequestDTO requestBody) {
        return given(twitchAuthRequestSpec)
                .body(requestBody)
                .when()
                .post()
                .then()
                .spec(OkResponseSpec)
                .extract().as(AuthResponseDTO.class);
    }

    @Step("Get auth token with incorrect auth data")
    public static ErrorResponseDTO getErrorAuthResponse(AuthRequestDTO requestBody) {
        return given(twitchAuthRequestSpec)
                .body(requestBody)
                .when()
                .post()
                .then()
                .spec(badRequestResponseSpec)
                .extract().as(ErrorResponseDTO.class);
    }

    @Step("Create new schedule")
    public static ScheduleResponseDTO createBroadCastSchedule(ScheduleRequestDTO requestBody, String broadcasterId) {
        return given(twitchRequestSpec)
                .queryParam("broadcaster_id", broadcasterId)
                .body(requestBody)
                .when()
                .post("/schedule/segment")
                .then()
                .spec(OkResponseSpec)
                .extract().as(ScheduleResponseDTO.class);
    }

    @Step("Delete schedule")
    public static void deleteBroadCastSchedule(String broadcasterId, String scheduleId) {
        given(twitchRequestSpec)
                .queryParam("broadcaster_id", broadcasterId)
                .queryParam("id", scheduleId)
                .when()
                .delete("/schedule/segment")
                .then()
                .spec(NoContentResponseSpec);
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
    public static StreamsResponseDTO getAllStreamsWithLanguage(String language) {
        return given(twitchRequestSpec)
                .queryParam("language", language)
                .when()
                .get("/streams")
                .then()
                .spec(OkResponseSpec)
                .extract().as(StreamsResponseDTO.class);
    }

    @Step("Retrieve a user by id by sending GET request")
    public static UserResponseDTO getUserById(String id) {
        return given(twitchRequestSpec)
                .queryParam("id", id)
                .when()
                .get("/users")
                .then()
                .spec(OkResponseSpec)
                .extract().as(UserResponseDTO.class);
    }

    @Step("Retrieve a user by unknown id: {0} by sending GET request")
    public static ErrorResponseDTO getUserByUnknownId(String id) {
        return given(twitchRequestSpec)
                .queryParam("id", id)
                .when()
                .get("/users")
                .then()
                .spec(badRequestResponseSpec)
                .extract().as(ErrorResponseDTO.class);
    }

    @Step("Retrieve a video by sending GET request")
    public static VideoResponseDTO getSingleVideoById(String id) {
        return given(twitchRequestSpec)
                .queryParam("id", id)
                .when()
                .get("/videos")
                .then()
                .spec(OkResponseSpec)
                .extract().as(VideoResponseDTO.class);
    }

    @Step("Retrieve list of videos by sending GET request")
    public static VideoResponseDTO getListOfVideosByStreamerId(String streamerId) {
        return given(twitchRequestSpec)
                .queryParam("user_id", streamerId)
                .when()
                .get("/videos")
                .then()
                .spec(OkResponseSpec)
                .extract().as(VideoResponseDTO.class);
    }

    @Step("Retrieve a video with id: {0} by sending GET request")
    public static ErrorResponseDTO getListOfVideosByUnknownStreamerId(String streamerId) {
        return given(twitchRequestSpec)
                .queryParam("user_id", streamerId)
                .when()
                .get("/videos")
                .then()
                .spec(badRequestResponseSpec)
                .extract().as(ErrorResponseDTO.class);
    }
}
