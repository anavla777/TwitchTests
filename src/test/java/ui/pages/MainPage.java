package ui.pages;

import com.codeborne.selenide.SelenideElement;
import common.data.LanguageData;
import ui.pages.components.LanguageSwitcher;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private final SelenideElement
            searchInput = $("[data-a-target='tw-input']"),
            streamLink = $("[data-test-selector='StreamTitle']"),
            moreOptionsMenu = $("[data-a-target='ellipsis-button']"),
            developersLink = $("[data-a-target='developers-link']");


    private final LanguageSwitcher languageSwitcher = new LanguageSwitcher();

    public void checkLocalization(LanguageData languageData) {
        $(languageData.getBrowseLink()).shouldHave(text(languageData.getBrowse()));
        $(languageData.getLoginButton()).shouldHave(text(languageData.getLogin()));
        $(languageData.getSignUpButton()).shouldHave(text(languageData.getRegister()));
        $(languageData.getLinkToActive()).shouldHave(text(languageData.getActive()));
        $(languageData.getLiveIndicator()).shouldHave(text(languageData.getLive()));
    }

    public void switchLanguage(String language) {
        languageSwitcher.selectLanguage(language);
    }

    public void openMenu() {
        moreOptionsMenu.click();
    }

    public void checkThatDevOptionExist() {
        developersLink.should(exist);
    }

    public void openMainPage() {
        open("/");
    }

    public void findContentBySearchQuery(String query) {
        searchInput.setValue(query).hover().pressEnter();
    }

    public void openLiveStream() {
        streamLink.click();
    }
}
