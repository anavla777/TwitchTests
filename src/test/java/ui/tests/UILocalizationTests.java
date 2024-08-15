package ui.tests;

import common.data.LanguageData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.qameta.allure.Allure.step;

@Tag("UI")
@Feature("Localization")
@Owner("Ananenkov Vladislav")
@DisplayName("UI: Localization tests")
public class UILocalizationTests extends TestBaseUI {

    @ParameterizedTest(name = "Twitch Main Page has correct text on {0} language")
    @MethodSource("common.utils.CsvUtils#provideLanguageData")
    @DisplayName("UI: Twitch Main Page has correct text on RU and EN languages")
    void twitchMainPageShouldDisplayCorrectTextTest(LanguageData languageData) {
        step("Open main page",
                mainPage::openMainPage);
        step("Switch language to " + languageData.getLanguageCode(), () ->
                mainPage.switchLanguage(languageData.getLanguageCode()));
        step("Check translation for " + languageData.getLanguageCode(), () ->
                mainPage.checkLocalization(languageData));
    }
}
