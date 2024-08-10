package api.tests;

import common.config.APITestDataConfig;
import io.qameta.allure.*;
import api.models.ScheduleRequestDTO;
import api.models.ScheduleResponseDTO;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static api.specs.TwitchSpec.NoContentResponseSpec;
import static api.specs.TwitchSpec.twitchRequestSpec;

@DisplayName("API: Schedule managing tests")
@Tag("API")
@Owner("Vladislav Ananenkov")
@Feature("Schedule Management")
public class ScheduleApiTests extends TestBaseAPI {
    final APITestDataConfig testData = ConfigFactory.create(APITestDataConfig.class, System.getProperties());

    @Test
    @DisplayName("API: Check that new schedule can be created")
    void createNewStreamScheduleTest() {
        ZonedDateTime startTime = ZonedDateTime.now(ZoneId.of(testData.timezone())).plusHours(1)
                .truncatedTo(ChronoUnit.MINUTES);
        String formattedDate = startTime
                .format(DateTimeFormatter.ISO_INSTANT);

        ScheduleRequestDTO requestBody = new ScheduleRequestDTO();
        requestBody.setStartTime(formattedDate);
        requestBody.setTimezone(testData.timezone());
        requestBody.setDuration(30);
        requestBody.setRecurring(true);
        requestBody.setCategoryId(testData.categoryId());
        requestBody.setTitle(testData.titleEN());

        ScheduleResponseDTO response = APISteps
                .createBroadCastSchedule(requestBody, testData.broadcasterId());

        step("Verify schedule parameters", () -> {
            assertThat(response.getData().broadcaster_id).isEqualTo(testData.broadcasterId());
            assertThat(response.getData().broadcaster_login).isEqualTo(testData.broadcasterLogin());
            assertThat(ZonedDateTime.parse(response.getData().segments.get(0).start_time))
                    .isEqualTo(formattedDate);
            assertThat(ZonedDateTime.parse(response.getData().segments.get(0).end_time))
                    .isEqualTo(startTime.plusMinutes(requestBody.getDuration()));
            assertThat(response.getData().segments.get(0).title).isEqualTo(requestBody.getTitle());
        });
        APISteps.deleteBroadCastSchedule(
                response.getData().broadcaster_id,
                response.getData().segments.get(0).id
        );
    }

    @Test
    @DisplayName("API: Check that schedule can be deleted")
    void deleteBroadcastScheduleTest() {
        ZonedDateTime startTime = ZonedDateTime.now(ZoneId.of(testData.timezone())).plusHours(2)
                .truncatedTo(ChronoUnit.MINUTES);
        String formattedDate = startTime
                .format(DateTimeFormatter.ISO_INSTANT);
        ScheduleRequestDTO requestBody = new ScheduleRequestDTO();
        requestBody.setStartTime(formattedDate);
        requestBody.setTimezone(testData.timezone());
        requestBody.setDuration(30);
        requestBody.setRecurring(true);
        requestBody.setCategoryId(testData.categoryId());
        requestBody.setTitle(testData.titleSpacesEN());

        ScheduleResponseDTO response = APISteps
                .createBroadCastSchedule(requestBody, testData.broadcasterId());
        String deleteResponse =
                step("Delete created schedule", () ->
                        given(twitchRequestSpec)
                                .queryParam("broadcaster_id", testData.broadcasterId())
                                .queryParam("id", response.getData().segments.get(0).id)
                                .when()
                                .delete("/schedule/segment")
                                .then()
                                .spec(NoContentResponseSpec)
                                .extract().asString()
                );
        step("Verify that schedule was deleted (response body is empty)", () -> {
            assertThat(deleteResponse).isEqualTo("");
        });
    }
}
