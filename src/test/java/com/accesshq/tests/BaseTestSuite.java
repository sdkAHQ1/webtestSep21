package com.accesshq.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestSuite {
    protected WebDriver driver;

    @BeforeEach
    public void Setup() {
        driver = new ChromeDriver();
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
