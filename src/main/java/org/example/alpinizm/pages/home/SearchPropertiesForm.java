package org.example.alpinizm.pages.home;


import org.example.alpinizm.core.logger.AlpinizmLogger;
import org.example.alpinizm.pages.home.widgets.CalendarPopup;
import org.example.alpinizm.pages.home.widgets.GuestsSelectionPopup;
import org.example.alpinizm.pages.search.SearchResultsPage;
import org.example.alpinizm.selenium.WebDriverManager;
import org.example.alpinizm.selenium.webelements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SearchPropertiesForm {

    private static final AlpinizmLogger LOG = AlpinizmLogger.getLogger(SearchPropertiesForm.class);

    private static final Label formTitleLabel = WebDriverManager.getDriver().label(By.cssSelector("h1.cMptV"));
    private static final Label formSubtitleLabel = WebDriverManager.getDriver().label(By.cssSelector("p.kMthTr"));
    private static final Button searchButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-jVSGNQ .sc-giAqHp"));
    private static final Dropdown locationDropdown = WebDriverManager.getDriver()
            .dropdown(By.cssSelector(".sc-jYKCQm .sc-csTbgd"), By.cssSelector(".sc-iXquSf.VzqiF"), By.cssSelector("input.locationPicker"));
    private static final Button checkInCalendarButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-kGVuwA .sc-csTbgd"));
    private static final Button checkOutCalendarButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-bA-DTon .sc-csTbgd"));
    private static final Button guestsButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-bxLXlR .sc-gsWcmt"));
    private static final CheckBox amenitiesCheckBoxes = WebDriverManager.getDriver().checkbox(By.cssSelector(".sc-fWWYYk"), "fepHIR");

    private static final CalendarPopup checkInCalendar = new CalendarPopup("sc-kGVuwA");
    private static final CalendarPopup checkOutCalendar = new CalendarPopup(".sc-bA-DTon");

    public GuestsSelectionPopup guestsSelectionPopup() {
        return new GuestsSelectionPopup().waitForPopupToBeVisible();
    }

    public boolean isTitleLabelVisible() {
        formTitleLabel.waitForElementToBeVisibleSilently();
        return formTitleLabel.isDisplayed();
    }

    public boolean isSubTitleLabelVisible() {
        formSubtitleLabel.waitForElementToBeVisibleSilently();
        return formSubtitleLabel.isDisplayed();
    }

    public boolean isSearchButtonVisible() {
        searchButton.waitForElementToBeVisibleSilently();
        return searchButton.isDisplayed();
    }

    public boolean isLocationDropdownVisible() {
        locationDropdown.waitForElementToBeVisibleSilently();
        return locationDropdown.isDisplayed();
    }

    public List<String> getAmenitiesCheckBoxesNames() {
        amenitiesCheckBoxes.waitForElementToBeVisibleSilently();
        return amenitiesCheckBoxes.getCheckBoxes().getElementsTextList();
    }

    public boolean isAmenitiesCheckBoxSetByText(String txt) {
        amenitiesCheckBoxes.waitForElementToBeVisibleSilently();
        return amenitiesCheckBoxes.isCheckBoxSelectedByText(txt);
    }

    public boolean isCheckInCalendarVisible() {
        checkInCalendarButton.click();
        return checkInCalendar.isPopupVisible();
    }

    public boolean isCheckOutCalendarVisible() {
        checkOutCalendarButton.click();
        return checkOutCalendar.isPopupVisible();
    }

    public boolean isGuestSelectorVisible() {
        try {
            guestsButton.click();
        } catch (StaleElementReferenceException e) {
            LOG.warn("StaleElementReferenceException caught. Trying to click button ..:: Guests at Search Form ::.. once again.");
            guestsButton.click();
        }
        return new GuestsSelectionPopup().waitForPopupToBeVisible().isPopupVisible();
    }

    public String getTitleLabelText() {
        return formTitleLabel.getText();
    }

    public String getSubtitleLabelText() {
        return formSubtitleLabel.getText();
    }

    public String getLocationDropdownSelectedValue() {
        locationDropdown.waitForElementToBeVisibleSilently();
        return locationDropdown.getSelectedOptionValue();
    }

    public Date getCheckInCalendarDate() {
        return checkInCalendar.getSelectedDate();
    }

    public Date getCheckOutCalendarDate() {
        return checkOutCalendar.getSelectedDate();
    }

    public SearchPropertiesForm selectLocationByText(String txt) {
        locationDropdown.selectOptionByText(txt);
        return this;
    }

    public SearchPropertiesForm selectAmenitiesByTexts(List<String> amenities) {
        amenities.forEach(amenitiesCheckBoxes::selectCheckBoxesByText);
        return this;
    }

    public SearchPropertiesForm selectCheckInDate(LocalDate date) {
        return this;
    }

    public SearchPropertiesForm selectCheckOutDate(LocalDate date) {
        return this;
    }

    public GuestsSelectionPopup clickGuestsText() {
        guestsButton.click();
        return guestsSelectionPopup();
    }

    public SearchPropertiesForm setGuestsNumber(int guests) {
        GuestsSelectionPopup guestsSelectionPopup = clickGuestsText();

        int i = 0;
        while(guestsSelectionPopup.getGuestsNumber() != guests && i < guests*2) {
            guestsSelectionPopup.addGuest();
            i++;
        }
        guestsButton.click();
        return this;
    }

    public SearchPropertiesForm setPetsAllowed(boolean pets) {
        GuestsSelectionPopup guestsSelectionPopup = clickGuestsText();
        guestsSelectionPopup.selectPetsAllowedRadioButton(pets);
        guestsButton.click();
        return this;
    }

    public SearchResultsPage clickSearchButton() {
        searchButton.click();
        return new SearchResultsPage();
    }

}
