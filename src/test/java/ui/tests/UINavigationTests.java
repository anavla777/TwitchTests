package ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.pages.DevelopersPage;
import ui.pages.DocsPage;

import static io.qameta.allure.Allure.step;

@Tag("UI")
@Owner("Vladislav Ananenkov")
@Feature("Navigation")
@DisplayName("UI: Navigation between pages test suite")
public class UINavigationTests extends TestBaseUI {
    final DevelopersPage developersPage = new DevelopersPage();

    @Test
    @DisplayName("UI: Navigation to Developers page")
    public void navigateToDevelopersPageTest() {
        step("Open main page", mainPage::openPage);
        step("Expand additional options menu", mainPage::openMenu);
        step("Navigate to developers page", mainPage::navigateDevelopersPage);
        step("Verify content of Developers page", developersPage::verifyContentOfDevelopersPage);

    }

    @Test
    @DisplayName("UI: Navigation to Documentation page")
    public void navigateToDocumentationPageTest() {
        DocsPage docsPage = new DocsPage();
        step("Open main page", mainPage::openPage);
        step("Expand additional options menu", mainPage::openMenu);
        step("Navigate to developers page", mainPage::navigateDevelopersPage);
        step("Navigate to Documentation page", developersPage::navigateToDocsPage);
        step("Verify content of Documentation page", docsPage::verifyDocsPageContent);

    }
}
