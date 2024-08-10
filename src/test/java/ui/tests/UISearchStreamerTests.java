package ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

@Tag("UI")
@Feature("Broadcasts and videos")
@Owner("Ananenkov Vladislav")
@DisplayName("UI: Search streamer tests")
public class UISearchStreamerTests extends TestBaseUI {

    static Stream<Arguments> streamerShouldHaveCorrectNicknameAndVideoPlayerTest() {
        return Stream.of(
                Arguments.of("mL7support"),
                Arguments.of("shroud"),
                Arguments.of("PewDiePie")
        );
    }


    @MethodSource("streamerShouldHaveCorrectNicknameAndVideoPlayerTest")
    @ParameterizedTest(name = "Streamer {0} should have correct name and video player")
    @DisplayName("UI: Streamer should have correct name and video player")
    public void streamerShouldHaveCorrectNicknameAndVideoPlayerTest(String streamer) {
        step("Open main page", mainPage::openPage);
        step("Find " + streamer + " streamer by nickname", () ->
                mainPage.findStreamer(streamer));

        step("Streamer has correct name and video player", () ->
                streamerPage.verifyStreamer(streamer));
    }

    @Test
    @DisplayName("UI: Live stream should be opened")
    public void openLiveChannelTest() {
        step("Open main page", mainPage::openPage);
        step("Open first live stream", mainPage::openLiveStream);
        step("Check that stream has attributes of LIVE stream", streamerPage::checkLiveChannelAttributes);
    }
}
