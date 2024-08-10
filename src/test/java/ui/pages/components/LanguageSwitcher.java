package ui.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class LanguageSwitcher {
    private final SelenideElement
            userMenu = $("[data-a-target='user-menu-toggle']"),
            languageDropdown = $("[data-a-target='language-dropdown-link']");

    public void selectLanguage(String language) {
       userMenu.click();
       languageDropdown.click();
        $(by("data-language", language.toLowerCase())).click();
    }
}
