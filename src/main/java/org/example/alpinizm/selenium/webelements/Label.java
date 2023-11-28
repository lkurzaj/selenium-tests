package org.example.alpinizm.selenium.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Label extends BaseElement{

    public Label(By selector) {
        super(selector, Label.class);
    }

    public Label(By selector, int maxTimeout) {
        super(selector, Label.class, maxTimeout);
    }

    public Label(WebElement elem) {
        super(elem, Label.class);
    }

}
