package org.example.alpinizm.pages.home;

import org.example.alpinizm.pages.home.widgets.CalendarPopup;
import org.example.alpinizm.pages.home.widgets.GuestsSelectionPopup;
import org.example.alpinizm.pages.search.SearchResultsPage;
import org.example.alpinizm.selenium.WebDriverManager;
import org.example.alpinizm.selenium.webelements.*;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SearchPropertiesForm {
    private static final Label formTitleLabel = WebDriverManager.getDriver().label(By.cssSelector("h1.cMptV"));
    private static final Label formSubtitleLabel = WebDriverManager.getDriver().label(By.cssSelector("p.kMthTr"));
    private static final Button searchButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-jVSGNQ .sc-giAqHp"));
    private static final Dropdown locationDropdown = WebDriverManager.getDriver()
            .dropdown(By.cssSelector(".sc-jYKCQm .sc-eGJWMs"), By.cssSelector(".sc-iXquSf"), By.cssSelector("input.locationPicker"));
    private static final Button checkInCalendarButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-kGVuwA .sc-csTbgd"));
    private static final Button checkOutCalendarButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-bA-DTon .sc-csTbgd"));
    private static final Button guestsButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-bxLXlR .sc-csTbgd"));
    private static final CheckBox amenitiesCheckBoxes = WebDriverManager.getDriver().checkbox(By.cssSelector(".sc-fWWYYk"), "fepHIR");

    private static final CalendarPopup checkInCalendar = new CalendarPopup("sc-kGVuwA");
    private static final CalendarPopup checkOutCalendar = new CalendarPopup(".sc-bA-DTon");

    public GuestsSelectionPopup guestsSelectionPopup() {
        guestsButton.click();
        return new GuestsSelectionPopup().waitForPopupToBeVisible();
    }

    public boolean isTitleLabelVisible() {
        formTitleLabel.waitForElementToBeVisible();
        return formTitleLabel.isDisplayed();
    }

    public boolean isSubTitleLabelVisible() {
        formSubtitleLabel.waitForElementToBeVisible();
        return formSubtitleLabel.isDisplayed();
    }

    public boolean isSearchButtonVisible() {
        return searchButton.isDisplayed();
    }

    public boolean isLocationDropdownVisible() {
        return locationDropdown.isDisplayed();
    }

    public List<String> getAmenitiesCheckBoxesNames() {
        return amenitiesCheckBoxes.getCheckBoxes().getElementsTextList();
    }

    public boolean isAmenitiesCheckBoxSetByText(String txt) {
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
        guestsButton.click();
        return new GuestsSelectionPopup().waitForPopupToBeVisible().isPopupVisible();
    }

    public String getTitleLabelText() {
        return formTitleLabel.getText();
    }

    public String getSubtitleLabelText() {
        return formSubtitleLabel.getText();
    }

    public String getLocationDropdownSelectedValue() {
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

    public SearchPropertiesForm setGuestsNumber(int guests) {
        GuestsSelectionPopup guestsSelectionPopup = guestsSelectionPopup();

        int i = 0;
        while(guestsSelectionPopup.getGuestsNumber() != guests && i < guests*2) {
            guestsSelectionPopup.addGuest();
            i++;
        }
        guestsButton.click();
        return this;
    }

    public SearchPropertiesForm setPetsAllowed(boolean pets) {
        GuestsSelectionPopup guestsSelectionPopup = guestsSelectionPopup();
        guestsSelectionPopup.selectPetsAllowedRadioButton(pets);
        guestsButton.click();
        return this;
    }

    public SearchResultsPage clickSearchButton() {
        searchButton.click();
        return new SearchResultsPage();
    }

}
