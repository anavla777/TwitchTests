package common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/apiTestData.properties",
        "file:~/apiTestData.properties",
        "file:./apiTestData.properties"
})
public interface APITestDataConfig extends Config {
    @Key("video_id")
    String videoId();

    @Key("user_id")
    String userId();

    String streamLanguage();

    String streamerLogin();

    String broadcasterType();

    int broadcasterId();

    String broadcasterLogin();

    int categoryId();

    String timezone();

    String titleEN();

    String titleSpacesEN();

}
