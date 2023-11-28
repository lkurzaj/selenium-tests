package org.example.alpinizm.pages.home.widgets;

import org.example.alpinizm.selenium.WebDriverManager;
import org.example.alpinizm.selenium.webelements.Label;
import org.openqa.selenium.By;

import java.util.Date;

public class CalendarPopup {

    public static String baseSelectorClass;

    public CalendarPopup(String baseSelectorClazz) {
        baseSelectorClass = baseSelectorClazz;
    }
    private static final Label popup = WebDriverManager.getDriver().label(By.cssSelector(".sc-jXcxbT"));
    private static final Label selectedDate = WebDriverManager.getDriver()
            .label(By.cssSelector(String.format(".%s .sc-dFRpbK", baseSelectorClass)));

    public boolean isPopupVisible() {
        return popup.isDisplayed();
    }

    public Date getSelectedDate() {
        if (!selectedDate.isDisplayedImmediately()) {
            return null;
        }
        return null;
    }
}
