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
    String videoId();

    String streamerId();

    String streamLanguage();

    String streamerLogin();

    String broadcasterType();

    String broadcasterId();

    String broadcasterLogin();

    int categoryId();

    String timezone();

    String titleEN();
    String titleSpacesEN();

}
