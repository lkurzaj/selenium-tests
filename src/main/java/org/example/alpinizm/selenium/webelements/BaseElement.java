package org.example.alpinizm.selenium.webelements;

import org.example.alpinizm.core.logger.AlpinizmLogger;
import org.example.alpinizm.selenium.WebDriverManager;
import org.example.alpinizm.selenium.webdriver.ICustomWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

public class BaseElement {

    private static final String WAITING_TIME_LOG_TEMPLATE = "Waiting for element %s ..:: %s ::..";
    private AlpinizmLogger LOG;
    private WebDriverWait wait;
    protected By webElementSelector;
    protected WebElement webElement;
    Instant initTime;

    protected BaseElement(By selector, Class<?> clazz) {
        this(selector, clazz, ICustomWebDriver.MAX_TIMEOUT_SECONDS);
    }

    protected BaseElement(By selector, Class<?> clazz, int maxTimeout) {
        this.webElementSelector = selector;
        this.LOG = AlpinizmLogger.getLogger(clazz);
        this.wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(maxTimeout));
    }

    protected BaseElement(WebElement elem, Class<?> clazz) {
        this.webElement = elem;
        this.LOG = AlpinizmLogger.getLogger(clazz);
        this.wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(ICustomWebDriver.MAX_TIMEOUT_SECONDS));
    }

    private WebElement getElementBySelector() {
        String logMessage = String.format(WAITING_TIME_LOG_TEMPLATE, "presence", this.webElementSelector.toString());
        Instant initTime = Instant.now();
        this.webElement = wait.until(ExpectedConditions.presenceOfElementLocated(this.webElementSelector));
        LOG.logWaitForElementTime(logMessage, initTime);
        return this.webElement;
    }

    private WebElement getElement() {
        if (webElement == null) {
            return this.getElementBySelector();
        } else {
            return this.webElement;
        }
    }

    private WebElement getElementImmediately() {
        if (webElement == null) {
            return WebDriverManager.getDriver().findElement(this.webElementSelector);
        } else {
            return this.webElement;
        }
    }

    protected String getDescription() {
        if (webElementSelector == null) {
            return this.webElement.toString();
        } else {
            return this.webElementSelector.toString();
        }
    }

    public void waitForElementToBePresent() {
        String logMessage = String.format(WAITING_TIME_LOG_TEMPLATE, "present", this.getDescription());
        this.initTime = Instant.now();
        this.webElement = wait.until(ExpectedConditions.presenceOfElementLocated(this.webElementSelector));
        LOG.logWaitForElementTime(logMessage, this.initTime);
    }

    public void waitForElementToBeVisible() {
        String logMessage = String.format(WAITING_TIME_LOG_TEMPLATE, "visible", this.getDescription());
        this.initTime = Instant.now();
        if (this.webElementSelector == null) {
            this.webElement = wait.until(ExpectedConditions.visibilityOf(this.webElement));
        } else {
            this.webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(this.webElementSelector));
        }

        LOG.logWaitForElementTime(logMessage, this.initTime);
    }

    public void waitForElementToBeVisibleSilently() {
        String logMessage = String.format(WAITING_TIME_LOG_TEMPLATE, "visible", this.getDescription());
        try {
            this.waitForElementToBeVisible();
        } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException e) {
            LOG.info("Exception was thrown: " + e.getStackTrace());
        } finally {
            LOG.logWaitForElementTime(logMessage, this.initTime);
        }
    }

    public void waitForElementToBeInvisible() {
        String logMessage = String.format(WAITING_TIME_LOG_TEMPLATE, "invisible", this.getDescription());
        this.initTime = Instant.now();

        if (this.webElementSelector == null) {
            wait.until(ExpectedConditions.invisibilityOf(this.webElement));
        } else {
            wait.until(ExpectedConditions.invisibilityOfElementLocated((this.webElementSelector)));
        }

        LOG.logWaitForElementTime(logMessage, this.initTime);
    }

    public void waitForElementToBeInvisibleSilently() {
        String logMessage = String.format(WAITING_TIME_LOG_TEMPLATE, "invisible", this.getDescription());
        try {
            this.waitForElementToBeInvisible();
        } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException e) {
            LOG.info("Exception was thrown: " + e.getStackTrace());
        } finally {
            LOG.logWaitForElementTime(logMessage, initTime);
        }
    }

    protected void waitForElementToBeClickable() {
        String logMessage = String.format(WAITING_TIME_LOG_TEMPLATE, "clickable", this.getDescription());
        this.initTime = Instant.now();
        if (this.webElementSelector == null) {
            this.webElement = wait.until(ExpectedConditions.elementToBeClickable(this.webElement));
        } else {
            this.webElement = wait.until(ExpectedConditions.elementToBeClickable(this.webElementSelector));
        }
        LOG.logWaitForElementTime(logMessage, this.initTime);
    }

    public void moveToElement() {
        this.waitForElementToBeVisible();
        this.LOG.info("Scrolling into element ..:: " + this.getDescription() + " ::.. ");
        new Actions(WebDriverManager.getDriver()).moveToElement(webElement).perform();
    }

    /**
     * Method clicks on element after
     * - waiting for element to be visible
     * - waiting for element to be clickable
     * - scrolling layout to element
     */
    public void click() {
        this.moveToElement();
        this.waitForElementToBeClickable();
        this.LOG.info("Clicking element ..:: " + this.getDescription() + " ::.. ");
        try {
            this.webElement.click();
        } catch (ElementClickInterceptedException e) {
            LOG.warn("Trying to click using JavaScript, because ElementClickInterceptedException was thrown during selenium's click.");
            clickProgramatically();
        }
    }

    private void clickProgramatically() {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getDriver();
        executor.executeScript("arguments[0].click();", getElement());
    }

    public String getText() {
        this.waitForElementToBeVisible();
        String visibleText = this.getElement().getText();
        this.LOG.debug("Element's ..:: " + this.getDescription() + " ::.. attribute text = '" + visibleText + "'");
        return visibleText;
    }

    public boolean isDisplayed() {
        try {
            return this.getElement().isDisplayed();
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isDisplayedImmediately() {
        try {
            return this.getElementImmediately().isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isSelected() {
        try {
            return this.getElement().isSelected();
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    protected String getAttribute(String attributeName) {
        WebElement tempElement = getElement();
        String attr = tempElement.getAttribute(attributeName) == null ? "" : tempElement.getAttribute(attributeName);
        this.LOG.debug("Element's ..:: " + this.getDescription() + " ::.. attribute " + attributeName + " = '" + attr + "'");
        return attr;
    }

    /**
     * Function gets webelement's value attribute
     *
     * @return value attribute value, otherwise empty string
     */
    public String getAttributeValue() {
        return this.getAttribute(WebElementAttribute.VALUE.get());
    }

    /**
     * Function gets webelement's textContent attribute
     *
     * @return textContent attribute, otherwise empty string
     */
    public String getAttributeTextContent() {
        return this.getAttribute(WebElementAttribute.TEXT_CONTENT.get());
    }

    /**
     * Funtion gets webelement's class attribute
     *
     * @return class attribute, otherwise empty string
     */
    public String getTextAttributeClass() {
        return this.getAttribute(WebElementAttribute.CLASS.get());
    }

    /**
     * Funtion gets webelement's class attribute
     *
     * @return href attribute, otherwise empty string
     */
    public String getTextAttributeHref() {
        return this.getAttribute(WebElementAttribute.HREF.get());
    }
}
