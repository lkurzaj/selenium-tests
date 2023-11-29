package org.example.alpinizm.tests.home.searchForm.visibility;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.alpinizm.BaseTest;
import org.example.alpinizm.pages.home.HomePage;
import org.example.alpinizm.pages.home.SearchPropertiesForm;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PresenceTest extends BaseTest {

    private static final HomePage homePage = new HomePage();
    private static final SearchPropertiesForm searchPropertiesForm = homePage.searchPropertiesForm();
    private static final String VISIBILITY = "Visibility";

    @BeforeAll
    public static void beforeAll() {
        new HomePage().load();
    }

    @Test
    @Epic(VISIBILITY)
    @DisplayName("Check 'Title Label' visibility of Search Form on Home Page")
    public void checkIfSearchFormTitleIsVisible() {
        assertThat(searchPropertiesForm.isTitleLabelVisible()).as("Form Title field is not visible").isTrue();
    }

    @Test
    @Epic(VISIBILITY)
    @DisplayName("Check 'Subtitle Label' visibility of Search Form on Home Page")
    public void checkIfSearchFormSubtitleIsVisible() {
        assertThat(searchPropertiesForm.isSubTitleLabelVisible()).as("Form Subtitle field is not visible").isTrue();
    }

    @Test
    @Epic(VISIBILITY)
    @DisplayName("Check 'Location Dropdown' visibility of Search Form on Home Page")
    public void checkIfSearchFormLocationDropdownIsVisible() {
        assertThat(searchPropertiesForm.isLocationDropdownVisible()).as("Location Dropdown List is not visible").isTrue();
    }

    @Test
    @Epic(VISIBILITY)
    @DisplayName("Check 'Check In Calendar' visibility of Search Form on Home Page")
    public void checkIfSearchFormCheckInCalendarIsVisible() {
        assertThat(searchPropertiesForm.isCheckInCalendarVisible()).as("Check In Calendar is not visible").isTrue();
    }

    @Test
    @Epic(VISIBILITY)
    @DisplayName("Check 'Check Out Calendar' visibility of Search Form on Home Page")
    public void checkIfSearchFormCheckOutCalendarIsVisible() {
        assertThat(searchPropertiesForm.isCheckOutCalendarVisible())
                .as("Check In Calendar is not visible").isTrue();
    }

    @Test
    @Epic(VISIBILITY)
    @DisplayName("Check 'Guests Selector' visibility of Search Form on Home Page")
    public void checkIfSearchFormGuestsSelectorIsVisible() {
        assertThat(searchPropertiesForm.isGuestSelectorVisible()).as("Guest Popup is not visible").isTrue();
    }

    @Test
    @Epic(VISIBILITY)
    @DisplayName("Check 'Search Button' visibility of Search Form on Home Page")
    public void checkIfSearchFormearchButtonIsVisible() {
        assertThat(searchPropertiesForm.isSearchButtonVisible())
                .as("Search Button is not visible").isTrue();
    }

    @Test
    @Epic(VISIBILITY)
    @DisplayName("Check 'Amenities Checkboxes' visibility of Search Form on Home Page")
    public void checkIfSearchFormAmenitiesCheckboxesIsVisible() {
        assertThat(searchPropertiesForm.getAmenitiesCheckBoxesNames())
                .as("Amenities CheckBoxes are not visible")
                .isEqualTo(Arrays.asList("Air conditioning", "Breakfast", "24-hour checkin", "Balcony", "Beach essentials"));
    }
}
