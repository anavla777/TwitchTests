package api.tests;

import api.models.ErrorResponseDTO;
import api.models.UserResponseDTO;
import api.steps.APISteps;
import common.config.APITestDataConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.specs.TwitchSpec.OkResponseSpec;
import static api.specs.TwitchSpec.badRequestResponseSpec;
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
        UserResponseDTO response = APISteps
                .getUserById(testData.userId())
                .spec(OkResponseSpec)
                .extract().as(UserResponseDTO.class);
        step("Verify user metadata", () -> {
            assertThat(response.getData()).hasSize(1);
            assertThat(response.getData().get(0).id).isEqualTo(testData.userId());
            assertThat(response.getData().get(0).login).isEqualTo(testData.streamerLogin());
            assertThat(response.getData().get(0).broadcaster_type).isEqualTo(testData.broadcasterType());
        });
    }

    @Test
    @DisplayName("API: Check data retrieval for unknown user id")
    void getSingleUserByUnknownIdTest() {
        ErrorResponseDTO response = APISteps.getUserById("null")
                .spec(badRequestResponseSpec)
                .extract().as(ErrorResponseDTO.class);
        step("Verify that error 400 is returned", () -> {
            assertThat(response.getStatus()).isEqualTo(400);
            assertThat(response.getMessage()).isEqualTo(
                    "Invalid username(s), email(s), or ID(s). Bad Identifiers.");
        });
    }

}
