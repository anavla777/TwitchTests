package pages.components;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class LanguageSwitcher {
    public void selectLanguage(String language) {
        $(by("data-a-target","user-menu-toggle")).click();
        $(by("data-a-target","language-dropdown-link")).click();
        $(by("data-language",language.toLowerCase())).click();
    }
}
