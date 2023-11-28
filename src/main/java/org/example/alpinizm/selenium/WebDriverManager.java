package org.example.alpinizm.selenium;

import org.example.alpinizm.selenium.webdriver.CustomWebDriver;
import org.example.alpinizm.selenium.webdriver.ICustomWebDriver;

public class WebDriverManager {

    private static final ThreadLocal<ICustomWebDriver> threadedWebDriver = new InheritableThreadLocal<ICustomWebDriver>();

    public static ICustomWebDriver getDriver() {
        ICustomWebDriver instance;

        if (WebDriverManager.threadedWebDriver.get() == null || WebDriverManager.threadedWebDriver.get().toString().contains("null")) {
            instance = CustomWebDriver.getCustomWebDriverInstance();
            WebDriverManager.threadedWebDriver.set(instance);
        }
        return WebDriverManager.threadedWebDriver.get();

    }
}
