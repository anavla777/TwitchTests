package ui.pages;

import com.codeborne.selenide.SelenideElement;
import data.Language;
import ui.pages.components.LanguageSwitcher;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement
            searchInput = $("[data-a-target='tw-input']"),
            signUpButton = $("[data-a-target='signup-button']"),
            loginButton = $("[data-a-target='login-button']"),
            streamLink = $("[data-test-selector='StreamTitle']"),
            browseLink = $("[data-a-target='browse-link']"),
            linkToActive = $("h2.tw-title"),
            liveIndicator = $(".tw-channel-status-text-indicator"),
            moreOptionsMenu = $("[data-a-target='ellipsis-button']"),
            developersLink = $("[data-a-target='developers-link']");


    private final LanguageSwitcher languageSwitcher = new LanguageSwitcher();

    public void checkLocalization(Language language) {
        browseLink.shouldHave(text(language.browse));
        loginButton.shouldHave(text(language.login));
        signUpButton.shouldHave(text(language.register));
        linkToActive.shouldHave(text(language.active));
        liveIndicator.shouldHave(text(language.live));
    }

    public void switchLanguage(String language) {
        languageSwitcher.selectLanguage(language);
    }

    public void openPage() {
        open("/");
    }

    public void openBrowsePage() {
        browseLink.click();
    }

    public void findStreamer(String streamer) {
        searchInput.setValue(streamer).hover().pressEnter();
        $(byText(streamer)).click();
    }

    public void openLiveStream() {
        streamLink.click();
    }

    public void openMenu() {
        moreOptionsMenu.click();
    }

    public void navigateDevelopersPage() {
        executeJavaScript("document.querySelector('a[data-a-target=\"developers-link\"]')" +
                ".setAttribute('target', '_self')");
        developersLink.click();
    }
}
