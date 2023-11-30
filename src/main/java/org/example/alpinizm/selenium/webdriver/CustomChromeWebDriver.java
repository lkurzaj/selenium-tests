package org.example.alpinizm.selenium.webdriver;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CustomChromeWebDriver extends ChromeDriver implements ICustomWebDriver {

    public CustomChromeWebDriver(){
        super();
    }

    public CustomChromeWebDriver(ChromeOptions opts){
        super(opts);
    }

    @Attachment(value = "Screenshot", type="image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) this).getScreenshotAs(OutputType.BYTES);
    }

}
