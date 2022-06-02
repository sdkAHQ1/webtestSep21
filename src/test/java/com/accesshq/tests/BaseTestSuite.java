package com.accesshq.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTestSuite {
    protected RemoteWebDriver driver;

    @BeforeEach
    public void Setup() throws MalformedURLException {
        // run via localhost against selenium grid if running through IDE
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "102.0.5005.63");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

        // driver = new RemoteWebDriver(new URL("http://172.18.0.2:4444/wd/hub"), new ChromeOptions());

        // run via selenium-hub domain name if running through Jenkins CI
        // driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), new ChromeOptions());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://d18u5zoaatmpxx.cloudfront.net/#/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void Cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
}
