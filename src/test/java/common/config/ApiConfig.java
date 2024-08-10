package common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api.properties",
        "file:~/api.properties",
        "file:./api.properties"
})

public interface ApiConfig extends Config {

    @DefaultValue("https://api.twitch.tv")
    String baseURI();

    @DefaultValue("/helix")
    String basePath();

    @DefaultValue("https://id.twitch.tv")
    String authBaseURI();

    @DefaultValue("/oauth2/token")
    String authBasePath();

}
