package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class DevelopersPage {
    private final SelenideElement
            devHeader = $("h1.editable"),
            createExtensionButton = $("a[href='https://dev.twitch.tv/console/extensions/create']"),
            playBookButton = $("a[href='https://dev.twitch.tv/gamedevelopers/']"),
            docsLink = $("a[href='https://dev.twitch.tv/docs/']"),
            loginButton = $("a[href='https://dev.twitch.tv/login?next=https%3A%2F%2Fdev.twitch.tv%2F']");

    public void verifyContentOfDevelopersPage() {
        devHeader.shouldHave(text("Build the Future of Live Entertainment"));
        createExtensionButton.shouldHave(text("Create an Extension"));
        playBookButton.shouldHave(text("Explore the Playbook"));
        docsLink.shouldHave(text("Docs"));
        loginButton.shouldHave(text("Log in with Twitch"));
    }

    public void navigateToDocsPage() {
        docsLink.click();
    }
}
