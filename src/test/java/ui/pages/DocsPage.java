package ui.pages;

import com.codeborne.selenide.SelenideElement;
import ui.tests.TestBaseUI;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DocsPage extends TestBaseUI {
    private final SelenideElement
            developerHeader = $("#twitch-developer-documentation"),
            searchBar = $("#search-pop-btn"),
            changeLogHeader = $("#whats-new"),
            apiDocsLink = $("a[href='/docs/api']");

    public void verifyDocsPageContent() {
        developerHeader.shouldHave(text("Twitch Developer Documentation"));
        searchBar.shouldBe(visible);
        changeLogHeader.shouldHave(text("What’s New?"));
        apiDocsLink.shouldBe(clickable);
    }
}
