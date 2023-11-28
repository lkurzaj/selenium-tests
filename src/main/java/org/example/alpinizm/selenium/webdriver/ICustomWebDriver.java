package org.example.alpinizm.selenium.webdriver;

import org.example.alpinizm.core.properties.AlpinizmConstants;
import org.example.alpinizm.selenium.webelements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public interface ICustomWebDriver extends WebDriver {

    int MAX_TIMEOUT_MS = 5_000;
    int MAX_TIMEOUT_SECONDS = MAX_TIMEOUT_MS/1000;
    int TIMER_INTERVAL_MS = 50;

    static ICustomWebDriver getCustomWebDriverInstance() {
        String requestedWebDriver = AlpinizmConstants.environment().getBrowserName().toUpperCase();
        return Arrays.stream(CustomWebDriver.values())
                .filter(x -> x.name().equals(requestedWebDriver))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getCustomWebDriver();
    }

    default Label label(By selector) {
        return new Label(selector);
    }

    default Button button(By selector) {
        return new Button(selector);
    }

    default Dropdown dropdown(By selector, By optionSelector, By selectableOptionSelector) {
        return new Dropdown(selector, optionSelector, selectableOptionSelector);
    }

    default CheckBox checkbox(By selector, String selectedClass) {
        return new CheckBox(selector, selectedClass);
    }

    default WebElementList webelementList(By selector) {
        return new WebElementList(selector);
    }

}
