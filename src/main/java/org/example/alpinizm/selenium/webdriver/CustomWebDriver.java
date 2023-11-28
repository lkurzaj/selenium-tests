package org.example.alpinizm.selenium.webdriver;

import org.example.alpinizm.core.properties.AlpinizmConstants;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public enum CustomWebDriver {

    CHROME("CHROME") {
        @Override
        public ICustomWebDriver getCustomWebDriver() {
                ChromeOptions options = new ChromeOptions();
                return new CustomChromeWebDriver(options);
        }

    };

    String webDriverName;

    CustomWebDriver(String name) {
        this.webDriverName = name;
    }

    public static ICustomWebDriver getCustomWebDriverInstance() {
        String requestedWebDriver = AlpinizmConstants.environment().getBrowserName().toUpperCase();
        return Arrays.stream(CustomWebDriver.values())
                .filter(x -> x.name().equals(requestedWebDriver))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getCustomWebDriver();
    }

    public ICustomWebDriver getCustomWebDriver() {
        return new CustomChromeWebDriver();
    }

}
