package ui.tests;

import data.Language;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static io.qameta.allure.Allure.step;

@Tag("UI")
@Feature("Localization")
@Owner("Ananenkov Vladislav")
@DisplayName("UI: Localization tests")
public class UILocalizationTests extends TestBaseUI {

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Twitch Main Page has correct text on {0} streamLanguage")
    @DisplayName("UI: Twitch Main Page has correct text on RU and EN languages")
    void twitchMainPageShouldDisplayCorrectTextTest(Language language) {
        step("Open main page",
                mainPage::openPage);
        step("Switch streamLanguage to " + language.name(), () ->
                mainPage.switchLanguage(language.name()));
        step("Check translation for " + language.name(), () ->
                mainPage.checkLocalization(language));
    }
}
