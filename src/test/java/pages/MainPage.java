package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.LanguageSwitcher;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement
            searchInput = $("[data-a-target='tw-input']"),
            signUpButton= $("[data-a-target='signup-button']"),
            streamLink=$("[data-test-selector='StreamTitle']"),
            tagSearch=$("#dropdown-search-input");
    private final ElementsCollection
            searchResults=$$("[role='option']"),
            categoryCards=$$(".game-card");

    LanguageSwitcher languageSwitcher=new LanguageSwitcher();

    public void checkLocalization(String description) {
        signUpButton.shouldHave(text(description));
    }
    public void switchLanguage(String language) {
        languageSwitcher.selectLanguage(language);
    }

    public MainPage enterTag(String tag) {
        tagSearch.setValue(tag);
        searchResults.findBy(text(tag)).click();
        return this;
    }

    public void validateTag(String tag) {
        tagSearch.setValue(tag);
        searchResults.findBy(text(tag)).click();
        categoryCards.first().$("button").shouldHave(text(tag));
        categoryCards.get(1).$("button").shouldHave(text(tag));
        categoryCards.last(2).shouldHave(texts(tag,tag));
    }

    public MainPage openPage(String path) {
        open(path);
        return this;
    }
    public MainPage findCategory(String category) {
        $("[data-a-id='card-" + category.replaceAll("\\s","") + "']")
                .scrollIntoView(false).click();
        return this;
    }
    public void verifyCategory(String category) {
        $("h1").shouldHave(text(category));
    }
    public MainPage findStreamer(String streamer) {
        searchInput.setValue(streamer).pressEnter();
        $(byText(streamer)).click();
        return this;
    }

    public MainPage openLiveStream() {
        streamLink.click();
        return this;
    }

    public void checkLiveChannelAttributes(){
        $("[data-test-selector='video-player__video-layout']").shouldBe(visible);
        $(".tw-channel-status-text-indicator").shouldHave(text("LIVE"));
    }

    public void verifyStreamer(String streamer) {
        $("h1").shouldHave(text(streamer));
        $("[data-test-selector='video-player__video-layout']").shouldBe(visible);
    }
}
