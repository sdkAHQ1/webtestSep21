package com.accesshq.tests;

import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTestSuite {

    static WebDriver driver;

    @BeforeAll
    public static void Setup()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://d18u5zoaatmpxx.cloudfront.net/#/");
    }

    @AfterAll
    public static void Cleanup()
    {
        if (driver!=null) {
            driver.quit();
        }
    }

    @Test
    public void DemoTest()
    {
        //arrange

        // act
        var actualText = driver.findElement(By.cssSelector("h1.display-1")).getText();

        // assert
        Assertions.assertEquals("Web Playground", actualText);

    }

    @Test
    public void MovingButtonTest()
    {
        //arrange

        // act
        String expected = "CLICK ME DOWN!";
        WebElement button = driver.findElement(By.cssSelector("a[role=button]"));
        button.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.textToBePresentInElement(button,expected));

        // assert
        Assertions.assertEquals("CLICK ME DOWN!", button.getText());
    }

    @Test
    public void TableQuantityTest()
    {
        // arrange

        // act
        var tableElements = driver.findElement(By.tagName("table"));
        List<WebElement> inputElements = tableElements.findElements(By.tagName("input"));

        // assert
        Assertions.assertEquals(1, Integer.parseInt(inputElements.get(0).getAttribute("value")));

    }
}
