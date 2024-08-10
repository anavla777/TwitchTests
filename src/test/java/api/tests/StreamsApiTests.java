package api.tests;

import common.config.APITestDataConfig;
import io.qameta.allure.*;
import api.models.StreamsResponseDTO;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API: Live stream retrieval tests")
@Tag("API")
@Owner("Vladislav Ananenkov")
@Feature("Streams management API")
public class StreamsApiTests extends TestBaseAPI {
    @Test
    @DisplayName("API: Check that the list of streams is returned")
    void getAllStreamsTest() {
        StreamsResponseDTO response = APISteps.getAllStreams();
        step("Verify id and started_at is not null", () -> {
            assertThat(response.getData()).hasSizeGreaterThanOrEqualTo(1);
            assertThat(response.getData().get(0).id).isNotNull();
            assertThat(response.getData().get(0).started_at).isNotNull();
            assertThat(response.getData().get(1).id).isNotNull();
            assertThat(response.getData().get(1).started_at).isNotNull();
            assertThat(response.getData().get(response.getData().size()-1).id).isNotNull();
            assertThat(response.getData().get(response.getData().size()-1).started_at).isNotNull();
        });
    }

    @Test
    @DisplayName("API: Check that all streams filtered by specified streamLanguage are returned")
    @Story("Testing of getting stream using method GET")
    void getAllStreamsWithSpecifiedLanguageTest() {
        APITestDataConfig testData = ConfigFactory.create(APITestDataConfig.class, System.getProperties());
        StreamsResponseDTO response = APISteps.getAllStreamsWithLanguage(testData.streamLanguage());
        step("Verify that streams contain requested streamLanguage", () -> {
            assertThat(response.getData()).hasSizeGreaterThanOrEqualTo(1);
            assertThat(response.getData().get(0).language).isEqualTo(testData.streamLanguage());
            assertThat(response.getData().get(1).language).isEqualTo(testData.streamLanguage());
            assertThat(response.getData().get(response.getData().size()-1).language).isEqualTo(testData.streamLanguage());
        });
    }
}
