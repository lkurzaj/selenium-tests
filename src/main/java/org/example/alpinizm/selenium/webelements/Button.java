package org.example.alpinizm.selenium.webelements;

import org.example.alpinizm.core.logger.AlpinizmLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    public static AlpinizmLogger LOG = AlpinizmLogger.getLogger(Button.class);

    public Button(By selector) {
        super(selector, Button.class);
    }

    public Button(WebElement elem) {
        super(elem, Button.class);
    }

}
