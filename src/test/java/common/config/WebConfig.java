package common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${env}.properties",
        "file:~/${env}.properties",
        "file:./${env}.properties"
})

public interface WebConfig extends Config {

    @DefaultValue("https://twitch.tv")
    String baseUrl();

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("1920x1080")
    String browserSize();

    @DefaultValue("126")
    String browserVersion();

    @DefaultValue("false")
    boolean isRemote();

    @DefaultValue("localhost:4444")
    String remoteUrl();

    String selenoidUser();

    String selenoidPass();
}
