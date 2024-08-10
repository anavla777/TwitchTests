package ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

@Tag("UI")
@Owner("Vladislav Ananenkov")
@Feature("Filtration")
@DisplayName("UI: Stream filtration tests")
public class UIFiltrationTests extends TestBaseUI {

    private static Stream<Arguments> categoryShouldHaveCorrectTags() {
        return Stream.of(
                Arguments.of("Driving/Racing Game"),
                Arguments.of("Open World"),
                Arguments.of("RPG")
        );
    }

    @ValueSource(strings = {"Dota 2", "League of Legends", "Fortnite"})
    @ParameterizedTest(name = "Selected category should have {0} name")
    @DisplayName("UI: Selected category should have correct category name")
    public void categoryPageShouldDisplayCorrectTextTest(String category) {
        step("Open main page", mainPage::openPage);

        step("Open Browse page", mainPage::openBrowsePage);

        step("Find " + category + " category", () ->
                browsePage.findCategory(category));
        step("Verify name of selected category", () ->
                browsePage.verifyCategory(category));
    }

    @ParameterizedTest(name = "Filtered categories should have {0} tag")
    @MethodSource("categoryShouldHaveCorrectTags")
    @DisplayName("UI: Filtered categories should have specified tag")
    public void filterByTagsTest(String tag) {
        step("Open main page", mainPage::openPage);
        step("Navigate to Browse page", mainPage::openBrowsePage);

        step("Enter tag", () ->
                browsePage.enterTag(tag));

        step("Check that category has " + tag + " tag", () ->
                browsePage.validateTag(tag));
    }
}
