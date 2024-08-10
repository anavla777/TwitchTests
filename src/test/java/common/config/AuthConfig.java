package common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/auth.properties",
        "file:~/auth.properties",
        "file:./auth.properties"
})

public interface AuthConfig extends Config {

    @Key("client_id")
    String clientId();

    @Key("client_secret")
    String clientSecret();

    @Key("grant_type")
    String grantType();

    @Key("user_client_id")
    String userClientId();

    @Key("user_token")
    String userToken();


}
