package org.example.alpinizm.pages;

import org.example.alpinizm.core.logger.AlpinizmLogger;
import org.example.alpinizm.selenium.WebDriverManager;
import org.example.alpinizm.selenium.webdriver.ICustomWebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class AlpinizmBasePage {

    private static final String IS_DOCUMENT_ALREADY_LOADED_JS_SCRIPT= "return document.readyState === 'complete'";
    private static final AlpinizmLogger logger = AlpinizmLogger.getLogger(AlpinizmBasePage.class);

    private boolean isDocumentAlreadyLoaded(){
        return (boolean) ((JavascriptExecutor) WebDriverManager.getDriver())
                .executeScript(IS_DOCUMENT_ALREADY_LOADED_JS_SCRIPT);
    }

    protected ICustomWebDriver getDriver(){
        return WebDriverManager.getDriver();
    }

    protected void waitForPageToBeLoaded() {
        int timer_ms = 0;
        while(timer_ms <= ICustomWebDriver.MAX_TIMEOUT_MS && !isDocumentAlreadyLoaded()) {
            try {
                Thread.sleep(ICustomWebDriver.TIMER_INTERVAL_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer_ms += ICustomWebDriver.TIMER_INTERVAL_MS;
        }
    }

    public void loadUrl(String url){
        logger.info("Opening url: " + url);
        getDriver().get(url);
        waitForPageToBeLoaded();
    }

}
