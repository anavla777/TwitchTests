package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.LanguageSwitcher;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement
            searchInput = $("[data-a-target='tw-input']"),
            signUpButton = $("[data-a-target='signup-button']"),
            streamLink = $("[data-test-selector='StreamTitle']"),
            tagSearch = $("#dropdown-search-input"),
            browseLink = $("[data-a-target='browse-link']");
    private final ElementsCollection
            searchResults = $$("[role='option']"),
            categoryCards = $$(".game-card");

    private final LanguageSwitcher languageSwitcher = new LanguageSwitcher();

    public void checkLocalization(String description) {
        signUpButton.shouldHave(text(description));
    }

    public void switchLanguage(String language) {
        languageSwitcher.selectLanguage(language);
    }

    public void enterTag(String tag) {
        tagSearch.setValue(tag);
        searchResults.findBy(text(tag)).click();
    }

    public void validateTag(String tag) {
        categoryCards.first().$(".tw-tag").shouldHave(exactText(tag));
        categoryCards.get(1).$(".tw-tag").shouldHave(exactText(tag));
        categoryCards.last().$(".tw-tag").shouldHave(exactText(tag));
    }

    public void openPage() {
        open("/");
    }

    public void openBrowsePage() {
        browseLink.click();
    }

    public void findCategory(String category) {
        $("[data-a-id='card-" + category.replaceAll("\\s", "") + "']")
                .scrollIntoView(false).click();
    }

    public void verifyCategory(String category) {
        $("h1").shouldHave(exactText(category));
    }

    public void findStreamer(String streamer) {
        searchInput.setValue(streamer).hover().pressEnter();
        $(byText(streamer)).click();
    }

    public void openLiveStream() {
        streamLink.click();
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
