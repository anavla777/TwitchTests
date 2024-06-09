package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import tv.twitch.tests.data.Language;

import java.util.stream.Stream;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Owner("Ananenkov Vladislav")
@DisplayName("Twitch web tests")
public class TwitchWebTest extends TestBase {
    MainPage mainpage = new MainPage();
    @EnumSource(Language.class)
    @ParameterizedTest(name ="Twitch Main Page has correct text on {0} language")
    @Tag("WEB")
    @DisplayName("Twitch Main Page has correct text on RU and EN languages")
    void twitchMainPageShouldDisplayCorrectTextTest(Language language) {
        step("Open main page", () -> {
            mainpage.openPage();
        });
        step ("Switch language to " + language.name(), () -> {
            mainpage.switchLanguage(language.name());
        });
        step("Check translation for " +language.name(), () -> {
            mainpage.checkLocalization(language.description);
                });
    }

    @ValueSource(strings = {"Heroes of Might and Magic III: The Restoration of Erathia", "League of Legends", "Fortnite"})
    @ParameterizedTest(name="Selected category should have {0} name")
    @Tag("WEB")
    @DisplayName("Selected category should have correct category name")
    public void categoryPageShouldDisplayCorrectTextTest(String category) {
        step("Open main page", () -> {
            mainpage.openPage();
        });

        step("Open Browse page", () -> {
            mainpage.openBrowsePage();
        });

        step("Find " + category + " category",()->{
            mainpage.findCategory(category);
        });
        step("Verify name of selected category",()->{
            mainpage.verifyCategory(category);
        });
    }

    @MethodSource("streamerShouldHaveCorrectNicknameAndVideoPlayerTest")
    @ParameterizedTest(name="Streamer {0} should have correct name and video player")
    @Tag("WEB")
    @DisplayName("Streamer should have correct name and video player")
    public void streamerShouldHaveCorrectNicknameAndVideoPlayerTest(String streamer) {
        step("Open main page", () -> {
            mainpage.openPage();
        });
        step("Find " + streamer + " streamer by nickname", () -> {
            mainpage.findStreamer(streamer);
        });

        step("Streamer has correct name and video player", () -> {
            mainpage.verifyStreamer(streamer);
        });
    }

    @ParameterizedTest(name="Filtered categories should have {0} tag")
    @MethodSource("categoryShouldHaveCorrectTags")
    @Tag("WEB")
    @DisplayName("Filtered categories should have specified tag")
    public void filterByTagsTest(String tag) {
        step("Open main page", () -> {
            mainpage.openPage();
        });
        step("Navigate to Browse page", () -> {
            mainpage.openBrowsePage();
        });

        step("Enter tag",() ->{
            mainpage.enterTag(tag);
        });

        step("Check that category has " + tag +" tag",()->{
            mainpage.validateTag(tag);
        });
    }

    @Test
    @Tag("WEB")
    @DisplayName("Live stream should be opened")
    public void openLiveChannelTest() {
        step("Open main page",()->{
            mainpage.openPage();
        });
        step("Open first live stream",()->{
            mainpage.openLiveStream();
        });

        step("Check that stream has attributes of LIVE stream",()->{
            mainpage.checkLiveChannelAttributes();
        });
    }

    static Stream<Arguments> streamerShouldHaveCorrectNicknameAndVideoPlayerTest(){
        return Stream.of(
                Arguments.of("Anakq"),
                Arguments.of("shroud"),
                Arguments.of("PewDiePie")
        );
    }
    private static Stream<Arguments> categoryShouldHaveCorrectTags() {
        return Stream.of(
                Arguments.of("Driving/Racing Game"),
                Arguments.of("Open World"),
                Arguments.of("RPG")
        );
    }
}
