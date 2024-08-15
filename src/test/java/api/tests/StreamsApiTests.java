package api.tests;

import api.models.StreamsResponseDTO;
import api.steps.APISteps;
import common.config.APITestDataConfig;
import common.utils.DateUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static api.specs.TwitchSpec.OkResponseSpec;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API: Live stream retrieval tests")
@Tag("API")
@Owner("Vladislav Ananenkov")
@Feature("Streams management API")
public class StreamsApiTests extends TestBaseAPI {
    final DateUtils dateUtils = new DateUtils();
    final ZonedDateTime currTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
    final int year = currTime.getYear();
    final int month = currTime.getMonthValue();
    final int day = currTime.getDayOfMonth();

    @Test
    @DisplayName("API: Check that the list of streams is returned")
    void getAllStreamsTest() {
        StreamsResponseDTO response = APISteps.getAllStreams();
        step("Verify that id is digits and started_at is current date", () -> {
            assertThat(response.getData()).hasSizeGreaterThanOrEqualTo(1);
            assertThat(response.getData().get(0).id).containsOnlyDigits();
            assertThat(dateUtils.extractYear(response.getData().get(0).started_at))
                    .isEqualTo(year);
            assertThat(dateUtils.extractDay(response.getData().get(0).started_at))
                    .isEqualTo(day);
            assertThat(dateUtils.extractMonth(response.getData().get(0).started_at))
                    .isEqualTo(month);
            assertThat(response.getData().get(1).id).containsOnlyDigits();
            assertThat(dateUtils.extractYear(response.getData().get(1).started_at))
                    .isEqualTo(year);
            assertThat(dateUtils.extractDay(response.getData().get(1).started_at))
                    .isEqualTo(day);
            assertThat(dateUtils.extractMonth(response.getData().get(1).started_at))
                    .isEqualTo(month);
            assertThat(response.getData().get(response.getData().size() - 1).id).containsOnlyDigits();
            assertThat(dateUtils.extractYear(response.getData().get(response.getData().size() - 1).started_at))
                    .isEqualTo(year);
            assertThat(dateUtils.extractDay(response.getData().get(response.getData().size() - 1).started_at))
                    .isEqualTo(day);
            assertThat(dateUtils.extractMonth(response.getData().get(response.getData().size() - 1).started_at))
                    .isEqualTo(month);
        });
    }

    @Test
    @DisplayName("API: Check that all streams filtered by specified streamLanguage are returned")
    void getAllStreamsWithSpecifiedLanguageTest() {
        APITestDataConfig testData = ConfigFactory.create(APITestDataConfig.class, System.getProperties());
        StreamsResponseDTO response = APISteps
                .getStreamsWithSpecifiedParameter("language", testData.streamLanguage())
                .spec(OkResponseSpec)
                .extract().as(StreamsResponseDTO.class);
        step("Verify that streams contain requested streamLanguage", () -> {
            assertThat(response.getData()).hasSizeGreaterThanOrEqualTo(1);
            assertThat(response.getData().get(0).language).isEqualTo(testData.streamLanguage());
            assertThat(response.getData().get(1).language).isEqualTo(testData.streamLanguage());
            assertThat(response.getData().get(response.getData().size() - 1).language)
                    .isEqualTo(testData.streamLanguage());
        });
    }
}
