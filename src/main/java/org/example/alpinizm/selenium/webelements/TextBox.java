package org.example.alpinizm.selenium.webelements;

import org.example.alpinizm.core.logger.AlpinizmLogger;
import org.example.alpinizm.selenium.webelements.exception.TextBoxException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TextBox extends BaseElement {
    public static AlpinizmLogger LOG = AlpinizmLogger.getLogger(TextBox.class);
    private static final String SETTING_TEXT_ERROR_MSG = "There was unable to set text '%s' into:  ..:: %s ::..";

    public TextBox(By selector) {
        super(selector, TextBox.class);
    }

    public TextBox(WebElement elem) {
        super(elem, TextBox.class);
    }

    public void setText(CharSequence... txt) {
        String textToSet = getCharSequenceArrayAsString(txt);
        waitForElementToBeClickable();
        TextBox.LOG.info("Setting value '" + textToSet + "' into element ..:: " + getDescription() + " ::.. ");
        this.webElement.clear();
        this.webElement.sendKeys(txt);
        if (txt.length > 1) {
            return;
        }
        TextBox.LOG.debug("Checking if value " + textToSet + " was set into element ..:: " + getDescription() + " ::.. ");
        if (!getText().equals(textToSet)) {
            if (!getAttributeValue().equals(textToSet)) {
                if (!getAttributeTextContent().equals(textToSet)) {
                    throw new TextBoxException(String.format(TextBox.SETTING_TEXT_ERROR_MSG, textToSet, getDescription()));
                }
            }
        }
    }

    private String getCharSequenceArrayAsString(CharSequence... array){
        return Arrays.stream(array).map(CharSequence::toString).collect(Collectors.joining(" ->"));
    }

}
