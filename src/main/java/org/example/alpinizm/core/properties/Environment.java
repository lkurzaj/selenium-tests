package org.example.alpinizm.core.properties;

import org.example.alpinizm.core.logger.AlpinizmLogger;

import java.net.URL;
import java.util.Properties;

public class Environment extends Properties {

    private static final String PROPERTY_NOT_FOUND_LOG = "System property '%s' not found";
    public static final String DEFAULT_VAL_LOG = PROPERTY_NOT_FOUND_LOG + ", so value '%s' was set by default";
    public static final String GIVEN_VAL_LOG = "System property '%s' found with value '%s' given by user";
    private static final String BROWSER_NAME_KEY = "webdriver.driver";
    private static Environment instance = null;

    private static final String DEFAULT_BROWSER = "CHROME";

    private String browserName;

    private static final AlpinizmLogger LOG = AlpinizmLogger.getLogger(Environment.class);


    private Environment() {
        super(System.getProperties());
        this.init();
    }

    static Environment getEnvironment() {
        if (instance == null)
            instance = new Environment();
        return instance;
    }


    private void init() {
        this.setBrowserName();
    }

    public String getBrowserName() {
        return this.browserName;
    }

    private void setBrowserName() {
        String tempBrowserName = System.getProperty(BROWSER_NAME_KEY);
        if (tempBrowserName == null) {
            LOG.info(String.format(DEFAULT_VAL_LOG, BROWSER_NAME_KEY, DEFAULT_BROWSER));
            browserName = DEFAULT_BROWSER;
        } else {
            LOG.info(String.format(GIVEN_VAL_LOG, BROWSER_NAME_KEY, tempBrowserName));
            browserName = tempBrowserName;

        }
    }
}
