package ui.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StreamerPage {

    public StreamerPage openStreamerPage(String streamerName) {
        open("/" + streamerName);
        return this;
    }

    public void checkLiveChannelAttributes() {
        $("[data-test-selector='video-player__video-layout']").shouldBe(visible);
        $(".tw-channel-status-text-indicator").shouldHave(exactText("LIVE"));
    }

    public void verifyStreamer(String streamer) {
        $("h1").shouldHave(text(streamer));
        $("[data-test-selector='video-player__video-layout']").shouldBe(visible);
    }
}
