package com.test.configs;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public WebDriverManager() {

        driverType = ConfigFileReader.getInstance().getBrowser();
        environmentType = ConfigFileReader.getInstance().getEnvironment();
    }

    public WebDriver getDriver() {

        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL:
                driver = createLocalDriver();
                break;
            case REMOTE:
                driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        if (driverType == DriverType.CHROME) {
            System.setProperty(CHROME_DRIVER_PROPERTY, ConfigFileReader.getInstance().getChromeDriverPath());
            driver = new ChromeDriver();
        }
        if (ConfigFileReader.getInstance().getBrowserWindowSize()) {
            driver.manage().window().maximize();
        }
        return driver;
    }
}


