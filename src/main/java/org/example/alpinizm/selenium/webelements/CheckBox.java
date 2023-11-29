package org.example.alpinizm.selenium.webelements;

import org.example.alpinizm.core.logger.AlpinizmLogger;
import org.example.alpinizm.selenium.webelements.exception.CheckBoxException;
import org.openqa.selenium.By;

public class CheckBox extends BaseElement {
    public static final AlpinizmLogger LOG = AlpinizmLogger.getLogger(CheckBox.class);

    WebElementList checkboxes;
    String selectedClass;

    public CheckBox(By selector){
        this(selector, "selected");
    }

    public CheckBox(By selector, String selectedClazz){
        super(selector, Dropdown.class);
        checkboxes = new WebElementList(selector);
        selectedClass = selectedClazz;
    }

    public boolean isCheckBoxSelectedByText(String txt) {
        return checkboxes.getElementByText(txt).isSelected();
    }

    public boolean isCheckBoxSelectedByIndex(int idx) {
        return checkboxes.getElementByIndex(idx).isSelected();
    }

    public void selectCheckBoxesByText(String txt) {
        if (isCheckBoxSelectedByText(txt)) {
            LOG.info("Checkbox with text '" + txt + "' was already selected. Skipping clicking.");
        } else {
            checkboxes.clickElementByText(txt);
        }
    }

    public void selectCheckBoxesByIndex(int idx) {
        if (isCheckBoxSelectedByIndex(idx)) {
            LOG.info("Checkbox with index '" + idx + "' was already selected. Skipping clicking.");
        } else {
            checkboxes.clickElementByIndex(idx);
        }
    }

    public void unselectCheckBoxesByText(String txt) {
        if (!isCheckBoxSelectedByText(txt)) {
            LOG.info("Checkbox with text '" + txt + "' was already unselected. Skipping clicking.");
        } else {
            checkboxes.getElementByText(txt).click();
            Dropdown.LOG.debug("Checking if checkbox with text '" + txt + "' was unselected in ..:: " + this.getDescription() + " ::.. ");
            if (isCheckBoxSelectedByText(txt)) {
                throw new CheckBoxException("Unable to unselect checkbox with text: " + txt + " in ..:: " + this.getDescription() + " ::.. ");
            }
        }
    }

    public WebElementList getCheckBoxes() {
        return checkboxes;
    }

}
