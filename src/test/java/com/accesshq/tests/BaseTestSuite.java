package com.accesshq.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTestSuite {
    protected RemoteWebDriver driver;

    @BeforeEach
    public void Setup() throws MalformedURLException {
        // driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
        driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), new ChromeOptions());
        // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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
