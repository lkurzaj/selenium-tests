package org.example.alpinizm.selenium.webelements;

import org.example.alpinizm.core.logger.AlpinizmLogger;
import org.example.alpinizm.selenium.WebDriverManager;
import org.example.alpinizm.selenium.webelements.exception.WebElementListException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class WebElementList {
    public static AlpinizmLogger LOG = AlpinizmLogger.getLogger(WebElementList.class);
    private By webElementsSelector;

    public WebElementList(By selector){
        this.webElementsSelector = selector;
    }

    public List<WebElement> getElements(){
        return WebDriverManager.getDriver().findElements(this.webElementsSelector);
    }

    public WebElement getElementByText(String txt){
        List<WebElement> list = getElements().stream()
                .filter(x -> new Button(x).isDisplayed())
                .filter(x -> x.getText().contentEquals(txt))
                .collect(Collectors.toList());
        if(list.size() == 0){
            throw new WebElementListException("There were no web elements matching to text: " + txt + " in " + getElementsTextList());
        } else if( list.size() > 1){
            LOG.warn("More than one element found with text: " + txt + ". Getting first meeting criteria.");
        }
        return list.get(0);
    }

    public WebElement getElementByIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Function expects positive integer which will be used as index for list operations");
        }
        List<WebElement> list = getElements();
        int listSize = list.size();
        if (listSize == 0) {
            throw new WebElementListException("There were no web elements matching to selector: " + this.webElementsSelector);
        } else if (listSize - 1 < index) {
            LOG.warn("IndexOutOfBound element with index: " + index + " not found in " + listSize + "-elements list.");
        }
        return list.get(index);
    }

    public List<String> getElementsTextList(){
        return getElements().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public int getElementsCount(){
        return getElements().size();
    }

    public List<String> getElementsValueList(){
        return getElements().stream().map(x -> x.getAttribute("value")).collect(Collectors.toList());
    }

    public void clickElementByText(String txt){
        LOG.info("Clicking list's element ..:: " + this.webElementsSelector + " ::.. by text: " + txt);
        new Button(getElementByText(txt)).click();
    }

    public void moveToElementByText(String txt){
        LOG.info("Scrolling into list's element ..:: " + this.webElementsSelector + " ::.. by text: " + txt);
        new Button(getElementByText(txt)).moveToElement();

    }

    public void setElementTextByIndex(int index, CharSequence... txt){
        LOG.info("Setting list's element text: " + txt + " by index: " + index + " ..:: " + this.webElementsSelector + " ::..");
        new TextBox(getElementByIndex(index)).setText(txt);
    }

    public void clickElementByIndex(int index){
        LOG.info("Clicking list's element by index: " + index + " for ..:: " + this.webElementsSelector + " ::..");
        new Button(getElementByIndex(index)).click();
    }

    public int getElementIndexByText(String txt){
        List<String> elements = getElementsTextList();
        int index = elements.indexOf(txt);
        if(index == -1){
            throw new WebElementListException("There were no web elements matching to text: '" + txt + "' in " + elements.toString());
        }
        return index;
    }

    public int getElementIndexByValue(String val){
        List<String> elements = getElementsValueList();
        int index = elements.indexOf(val);
        if(index == -1){
            throw new WebElementListException("There were no web elements matching to value: '" + val + "' in " + elements.toString());
        }
        return index;
    }
}
