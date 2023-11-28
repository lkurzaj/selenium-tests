package org.example.alpinizm.pages.home.widgets;

import org.example.alpinizm.selenium.WebDriverManager;
import org.example.alpinizm.selenium.webelements.Button;
import org.example.alpinizm.selenium.webelements.Label;
import org.example.alpinizm.selenium.webelements.WebElementList;
import org.openqa.selenium.By;

public class GuestsSelectionPopup {

    private static final Label popup = WebDriverManager.getDriver().label(By.cssSelector(".sc-irKDMX"));
    private static final Label guestsCounterLabel = WebDriverManager.getDriver().label(By.cssSelector(".sc-gkCoMD"));
    private static final Button addGuestButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-flUlpA.sc-iGkqmO"));
    private static final Button subtractGuestButton = WebDriverManager.getDriver().button(By.cssSelector(".sc-flUlpA.sc-eXuyPJ"));
    private static final Label selectedGuestsNumberLabel = WebDriverManager.getDriver().label(By.cssSelector(".sc-bxLXlR .sc-dFRpbK"));

    private static final WebElementList yesnoRadioButton = WebDriverManager.getDriver()
            .webelementList(By.cssSelector(".sc-kizEQm input"));

    public boolean isPopupVisible() {
        return popup.isDisplayed();
    }

    public GuestsSelectionPopup waitForPopupToBeVisible() {
        popup.waitForElementToBeVisible();
        return this;
    }

    public void selectPetsAllowedRadioButton(boolean pets) {
        if (pets) {
            yesnoRadioButton.clickElementByIndex(0);
        } else {
            yesnoRadioButton.clickElementByIndex(1);
        }
    }

    public boolean isYesRadioButtonSelected() {
        return yesnoRadioButton.getElementByIndex(0).getAttribute("checked") != null;
    }

    public boolean isNoRadioButtonSelected() {
        return yesnoRadioButton.getElementByIndex(1).getAttribute("checked") != null;
    }

    public void selectNoRadioButton() {
        yesnoRadioButton.clickElementByIndex(1);
    }

    public String getSelectedGuestsNumberLabelText() {
        return selectedGuestsNumberLabel.getText();
    }

    public int getGuestsNumber() {
        String text = guestsCounterLabel.getText();
        return Integer.parseInt(guestsCounterLabel.getText());
    }

    public void addGuest() {
        addGuestButton.click();
    }

    public void subtractGuest() {
        subtractGuestButton.click();
    }


}
