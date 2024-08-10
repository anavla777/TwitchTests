package api.tests;

import common.config.APITestDataConfig;
import io.qameta.allure.*;
import api.models.ErrorResponseDTO;
import api.models.UserResponseDTO;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API: User info retrieval tests")
@Tag("API")
@Owner("Vladislav Ananenkov")
@Feature("User management API")
public class UsersApiTests extends TestBaseAPI {
    @Test
    @DisplayName("API: Check data retrieval for existing user")
    void getSingleUserByIdTest() {
        APITestDataConfig testData = ConfigFactory.create(APITestDataConfig.class, System.getProperties());
        UserResponseDTO response = APISteps.getUserById(testData.streamerId());
        step("Verify user metadata", () -> {
            assertThat(response.getData()).hasSize(1);
            assertThat(response.getData().get(0).id).isEqualTo(testData.streamerId());
            assertThat(response.getData().get(0).login).isEqualTo(testData.streamerLogin());
            assertThat(response.getData().get(0).broadcaster_type).isEqualTo(testData.broadcasterType());
            assertThat(response.getData().get(0).created_at).isNotNull();
        });
    }

    @Test
    @DisplayName("API: Check data retrieval for unknown user id")
    void getSingleUserByUnknownIdTest() {
        ErrorResponseDTO response = APISteps.getUserByUnknownId("null");
        step("Verify that error 400 is returned", () -> {
            assertThat(response).isNotNull();
            assertThat(response.getStatus()).isEqualTo(400);
            assertThat(response.getMessage()).isEqualTo(
                    "Invalid username(s), email(s), or ID(s). Bad Identifiers.");
        });
    }

}
