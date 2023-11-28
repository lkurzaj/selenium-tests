package org.example.alpinizm.tests.home.searchForm.visibility;

import io.qameta.allure.Epic;
import org.example.alpinizm.BaseTest;
import org.example.alpinizm.pages.home.HomePage;
import org.example.alpinizm.pages.home.SearchPropertiesForm;
import org.example.alpinizm.pages.home.widgets.GuestsSelectionPopup;
import org.example.alpinizm.selenium.WebDriverManager;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultValuesTest extends BaseTest {

    private static final HomePage homePage = new HomePage();
    private static final SearchPropertiesForm searchPropertiesForm = homePage.searchPropertiesForm();
    private static final String DEFAULT_DATA = "Default Data";

    @BeforeAll
    public static void beforeAll() {
        new HomePage().load();
    }

    @Test
    @Epic(DEFAULT_DATA)

    @DisplayName("Check 'Title Label' default values of Search Form on Home Page")
    public void checkDefaultValuesForTitleLabel() {
        assertThat(searchPropertiesForm.getTitleLabelText()).isEqualTo("ASD");
    }

    @Test
    @Epic(DEFAULT_DATA)
    @DisplayName("Check 'Subtitle Label' default values of Search Form on Home Page")
    public void checkDefaultValuesForSubtitleLabel() {
        assertThat(searchPropertiesForm.getSubtitleLabelText()).isEqualTo("Subtitle");
    }

    @Test
    @Epic(DEFAULT_DATA)
    @DisplayName("Check 'Location Dropdown' default values of Search Form on Home Page")
    public void checkDefaultValuesForLocationDropdownLabel() {
        assertThat(searchPropertiesForm.getLocationDropdownSelectedValue()).isEqualTo("");
    }

    @Test
    @Epic(DEFAULT_DATA)
    @DisplayName("Check 'Check In Calendar' default values of Search Form on Home Page")
    public void checkDefaultValuesForCheckInDate() {
        assertThat(searchPropertiesForm.getCheckInCalendarDate()).as("Check In Date should not be selected by default").isNull();
    }

    @Test
    @Epic(DEFAULT_DATA)
    @DisplayName("Check 'Check Out Calendar' default values of Search Form on Home Page")
    public void checkDefaultValuesForCheckOutDate() {
        assertThat(searchPropertiesForm.getCheckOutCalendarDate()).as("Check Out Date should not be selected by default").isNull();
    }

    @Test
    @Epic(DEFAULT_DATA)
    @DisplayName("Check 'Guests Field' default values of Search Form on Home Page")
    public void checkDefaultValuesForGuestsField() {
        GuestsSelectionPopup guestsSelectionPopup = searchPropertiesForm.guestsSelectionPopup();
        assertThat(guestsSelectionPopup.getSelectedGuestsNumberLabelText()).isEqualTo("1");
        assertThat(guestsSelectionPopup.isNoRadioButtonSelected()).isTrue();
    }

    @Test
    @Epic(DEFAULT_DATA)
    @DisplayName("Check 'Amenities' default values of Search Form on Home Page")
    public void checkDefaultValuesForFilters() {
        Arrays.asList("Air conditioning", "Breakfast", "24-hour checkin", "Balcony", "Beach essentials")
                .forEach(a -> {
                    assertThat(searchPropertiesForm.isAmenitiesCheckBoxSetByText(a))
                            .as(String.format("Amenity '%s' is set, but should not be selected", a)).isFalse();
                });
    }

}

