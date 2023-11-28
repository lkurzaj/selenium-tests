package org.example.alpinizm.selenium.webelements;

public enum WebElementAttribute {

    VALUE("value"),
    CLASS("class"),
    TEXT_CONTENT("textContent"),
    HREF("href");

    String attributeName;

    WebElementAttribute(String attr) {
        this.attributeName = attr;
    }

    public String get() {
        return this.attributeName;
    }
}
