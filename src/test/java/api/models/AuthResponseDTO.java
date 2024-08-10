package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponseDTO {
    @JsonProperty("access_token")
    String accessToken;
    @JsonProperty("expires_in")
    Integer expiresIn;
    @JsonProperty("token_type")
    String tokenType;
}
