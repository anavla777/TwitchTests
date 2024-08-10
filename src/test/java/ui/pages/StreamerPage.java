package ui.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class StreamerPage {
    public void checkLiveChannelAttributes() {
        $("[data-test-selector='video-player__video-layout']").shouldBe(visible);
        $(".tw-channel-status-text-indicator").shouldHave(exactText("LIVE"));
    }

    public void verifyStreamer(String streamer) {
        $("h1").shouldHave(text(streamer));
        $("[data-test-selector='video-player__video-layout']").shouldBe(visible);
    }
}
