package org.example.alpinizm;

import org.example.alpinizm.pages.home.HomePage;
import org.example.alpinizm.selenium.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class BaseTest
{
    @BeforeEach
    public void beforeEach() {
        new HomePage().load();
        WebDriverManager.getDriver().manage().deleteAllCookies();
        WebDriverManager.getDriver().navigate().refresh();
        WebDriverManager.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void afterEach() {
        WebDriverManager.getDriver().close();
    }
}
