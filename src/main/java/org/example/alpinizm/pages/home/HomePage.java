package org.example.alpinizm.pages.home;

import io.qameta.allure.Attachment;
import org.example.alpinizm.pages.AlpinizmBasePage;
import org.example.alpinizm.selenium.WebDriverManager;

public class HomePage extends AlpinizmBasePage {

    public static String url = "https://kamil-demo.alpinizm.uz";


    public void load() {
        loadUrl(url);
        searchPropertiesForm().isTitleLabelVisible();

    }

    public SearchPropertiesForm searchPropertiesForm() {
        return new SearchPropertiesForm();
    }
}
