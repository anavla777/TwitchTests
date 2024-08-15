package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BrowsePage {
    private final SelenideElement
            tagSearch = $("#dropdown-search-input");
    private final ElementsCollection
            searchResults = $$("[role='option']"),
            categoryCards = $$(".game-card");

    public BrowsePage findCategory(String category) {
        $("[data-a-id='card-" + category.replaceAll("\\s", "") + "']")
                .scrollIntoView(false).click();
        return this;
    }

    public BrowsePage openBrowsePage() {
        open("/directory");
        return this;
    }

    public void verifyCategory(String category) {
        $("h1").shouldHave(exactText(category));
    }

    public void enterTag(String tag) {
        tagSearch.setValue(tag);
        searchResults.findBy(text(tag)).click();
    }

    public void validateTag(String tag) {
        categoryCards.first().$(".tw-tag").shouldHave(exactText(tag));
        categoryCards.get(1).$(".tw-tag").shouldHave(exactText(tag));
        categoryCards.last().$(".tw-tag").shouldHave(exactText(tag));
    }
}
