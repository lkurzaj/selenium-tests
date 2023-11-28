package org.example.alpinizm.pages.search;

import org.example.alpinizm.pages.AlpinizmBasePage;
import org.example.alpinizm.selenium.WebDriverManager;
import org.example.alpinizm.selenium.webelements.Label;
import org.openqa.selenium.By;

public class SearchResultsPage extends AlpinizmBasePage {

    private static final Label searchResultsHeader = WebDriverManager.getDriver().label(By.cssSelector("h1.sc-jrsJWt"));

    public int getFoundPropertiesNumber() {
        return Integer.parseInt(searchResultsHeader.getText().replace(" properties found", ""));
    }
}
