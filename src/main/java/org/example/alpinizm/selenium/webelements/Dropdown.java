package org.example.alpinizm.selenium.webelements;

import org.example.alpinizm.core.logger.AlpinizmLogger;
import org.example.alpinizm.selenium.webelements.exception.DropdownException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class Dropdown extends BaseElement {
    public static final AlpinizmLogger LOG = AlpinizmLogger.getLogger(Dropdown.class);

    private WebElementList options;
    private Label selectedOptionLabel;
    private By optionSelector;

    public Dropdown(By selector){
        super(selector, Dropdown.class);
    }

    public Dropdown(By selector, By optionSelector){
        this(selector, optionSelector, optionSelector);
    }

    public Dropdown(By selector, By optionSelector, By selectableOptionSelector){
        super(selector, Dropdown.class);
        this.options = new WebElementList(optionSelector);
        this.optionSelector = optionSelector;
        this.selectedOptionLabel = new Label(selectableOptionSelector);
    }

    public void selectOptionByText(String txt){
        this.click();
        new Label(optionSelector).waitForElementToBeVisible();
        this.options.clickElementByText(txt);
        Dropdown.LOG.debug("Checking if option with text '" + txt + "' was set in ..:: " + this.getDescription() + " ::.. ");
        if(!selectedOptionLabel.getAttributeValue().contentEquals(txt)){
            throw new DropdownException("Unable to set option with text: " + txt + " in ..:: " + this.getDescription() + " ::.. ");
        }
    }

    public String getSelectedOptionValue() {
        try {
            return selectedOptionLabel.getAttributeValue();
        } catch (TimeoutException e) {
            Dropdown.LOG.debug("Dropdown selected option was not found for ..:: " + this.getDescription() + " ::.. ");
            return "";
        }
    }

}
