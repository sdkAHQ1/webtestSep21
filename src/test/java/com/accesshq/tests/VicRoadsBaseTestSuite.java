package com.accesshq.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VicRoadsBaseTestSuite {
    protected WebDriver driver;

    @BeforeEach
    public void Setup() {
        driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.vicroads.vic.gov.au/registration/limited-use-permits/unregistered-vehicle-permits/get-an-unregistered-vehicle-permit");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void Cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
}
