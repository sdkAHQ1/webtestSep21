package com.accesshq.tests;

import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTestSuite {

    @Test
    public void DemoTest()
    {
        //arrange
        var driver = new ChromeDriver();
        driver.get("https://d18u5zoaatmpxx.cloudfront.net/#/");

        // act
        var actualText = driver.findElement(By.cssSelector("h1.display-1")).getText();

        // assert
        Assertions.assertEquals("Web Playground", actualText);

        // clean up
        driver.quit();

    }
}
