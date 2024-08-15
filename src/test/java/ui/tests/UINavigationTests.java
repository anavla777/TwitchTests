package ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("UI")
@Owner("Vladislav Ananenkov")
@Feature("Navigation")
@DisplayName("UI: Navigation between pages test suite")
public class UINavigationTests extends TestBaseUI {

    @Test
    @DisplayName("UI: Check that Developer option exists in menu")
    public void navigateToDocumentationPageTest() {
        step("Open main page", mainPage::openMainPage);
        step("Open menu", mainPage::openMenu);
        step("Open menu", mainPage::checkThatDevOptionExist);

    }
}
