package api.tests;

import common.config.AuthConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import api.models.AuthRequestDTO;
import api.models.AuthResponseDTO;
import api.models.ErrorResponseDTO;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("API: App auth token retrieval tests")
@Tag("API")
@Owner("Vladislav Ananenkov")
@Feature("Login")
public class LoginTests extends TestBaseAPI {
    final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @DisplayName("API: App token retrieval with relevant data")
    @Test
    void authWithRelevantDataTest() {
        AuthRequestDTO requestBody = new AuthRequestDTO();
        requestBody.setClientId(authConfig.clientId());
        requestBody.setClientSecret(authConfig.clientSecret());
        requestBody.setGrantType(authConfig.grantType());
        AuthResponseDTO response = APISteps.getAuthResponse(requestBody);
        step("Check that token is returned", () ->
                assertThat(response.getAccessToken()).isNotNull()
        );
    }

    @DisplayName("API: App token retrieval with incorrect data")
    @Test
    void authWithIncorrectDataTest() {
        AuthRequestDTO requestBody = new AuthRequestDTO();
        requestBody.setClientId("test");
        requestBody.setClientSecret("test");
        requestBody.setGrantType("test");
        ErrorResponseDTO response = APISteps.getErrorAuthResponse(requestBody);
        step("Check that error message is returned", () ->
                assertThat(response.getMessage()).isEqualTo("invalid client")
        );
    }
}
