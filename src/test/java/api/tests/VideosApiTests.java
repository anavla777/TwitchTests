package api.tests;

import common.config.APITestDataConfig;
import io.qameta.allure.*;
import api.models.ErrorResponseDTO;
import api.models.VideoResponseDTO;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API: Video retrieval tests")
@Tag("API")
@Owner("Vladislav Ananenkov")
@Feature("Video management API")
public class VideosApiTests extends TestBaseAPI {
    final APITestDataConfig testData = ConfigFactory.create(APITestDataConfig.class, System.getProperties());

    @Test
    @DisplayName("API: Check video retrieval by video id")
    void getSingleVideoByIdTest() {
        VideoResponseDTO response = APISteps.getSingleVideoById(testData.videoId());
        step("Verify video metadata", () -> {
            assertThat(response.getData()).hasSize(1);
            assertThat(response.getData().get(0).id).isEqualTo(testData.videoId());
            assertThat(response.getData().get(0).url).isEqualTo("https://www.twitch.tv/videos/"
                    + testData.videoId());
        });
    }

    @Test
    @DisplayName("API: Check list of videos retrieval for specified user")
    void getListOfVideosByStreamerIdTest() {
        VideoResponseDTO response = APISteps.getListOfVideosByStreamerId(testData.streamerId());
        step("Verify video metadata", () -> {
            assertThat(response.getData()).hasSizeGreaterThanOrEqualTo(1);
            assertThat(response.getData().get(0).id).isNotNull();
            assertThat(response.getData().get(0).url).isNotNull();
            assertThat(response.getData().get(1).id).isNotNull();
            assertThat(response.getData().get(1).url).isNotNull();
            assertThat(response.getData().getLast().id).isNotNull();
            assertThat(response.getData().getLast().url).isNotNull();
        });
    }

    @Test
    @DisplayName("API: Check video retrieval for non-existing streamer")
    void getListOfVideosByUnknownStreamerIdTest() {
        ErrorResponseDTO response = APISteps.getListOfVideosByUnknownStreamerId("test");
        step("Verify thar error with unknown id is returned", () -> {
            assertThat(response.getMessage()).isEqualTo("ChannelId is not a valid number test");
            assertThat(response.getStatus()).isEqualTo(400);
        });
    }
}
