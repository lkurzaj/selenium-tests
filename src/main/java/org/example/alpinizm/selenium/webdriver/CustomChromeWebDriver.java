package org.example.alpinizm.selenium.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CustomChromeWebDriver extends ChromeDriver implements ICustomWebDriver {

    public CustomChromeWebDriver(){
        super();
    }

    public CustomChromeWebDriver(ChromeOptions opts){
        super(opts);
    }

}
