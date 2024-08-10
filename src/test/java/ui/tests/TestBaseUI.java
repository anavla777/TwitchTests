package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.config.WebConfig;
import common.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import ui.pages.BrowsePage;
import ui.pages.MainPage;
import ui.pages.StreamerPage;

import java.util.Map;

public class TestBaseUI {
    public final BrowsePage browsePage;
    public final MainPage mainPage;
    public final StreamerPage streamerPage;

    public TestBaseUI() {
        browsePage = new BrowsePage();
        mainPage = new MainPage();
        streamerPage = new StreamerPage();
    }

    @BeforeAll
    static void beforeAll() {
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.browserVersion = webConfig.browserVersion();
        if (webConfig.isRemote()) {
            Configuration.remote = "https://" + webConfig.selenoidUser() + ":"
                    + webConfig.selenoidPass() +
                    "@" + webConfig.remoteUrl()
                    + "/wd/hub";
        }
        if (webConfig.browser().equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--lang=en-US");
        }

        Configuration.pageLoadStrategy = "eager";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (!Configuration.browser.equals("firefox")) {
            Attach.browserConsoleLogs();
        }
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
