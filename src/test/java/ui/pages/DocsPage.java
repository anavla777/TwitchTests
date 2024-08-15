package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ui.tests.TestBaseUI;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DocsPage extends TestBaseUI {
    private final SelenideElement
            searchBar = $("#search-pop-btn");
    private final ElementsCollection
            searchResults = $$(".algolia-docsearch-suggestion--title");

    public DocsPage openDocsPage() {
        open("https://dev.twitch.tv/docs");
        return this;
    }

    public void findContentBySearchQuery(String query) {
        searchBar.setValue(query);
    }

    public void checkSearchResultForQuery(String query) {
        searchResults.first().scrollIntoView(false).shouldHave(text(query));
        searchResults.get(1).scrollIntoView(false).shouldHave(text(query));
        searchResults.last().scrollIntoView(false).shouldHave(text(query));
    }
}
