package org.example.alpinizm.pages.home;

import org.example.alpinizm.pages.AlpinizmBasePage;

public class HomePage extends AlpinizmBasePage {

    public static String url = "https://kamil-demo.alpinizm.uz";

    public void load() {
        loadUrl(url);
    }

    public SearchPropertiesForm searchPropertiesForm() {
        return new SearchPropertiesForm();
    }
}
