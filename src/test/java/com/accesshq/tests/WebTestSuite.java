package com.accesshq.tests;

import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;
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

    @Test
    public void FormsErrorMsgTest()
    {
        // arrange - click on forms menu item to navigate to Forms page
        navigateToFormsPage();
        clickSubmitButton();

        // assert - error messages are correct
        Assertions.assertEquals("Your name is required", driver.findElement(By.cssSelector("[id='name-err']")).getText());
        Assertions.assertEquals("Your email is required", driver.findElement(By.cssSelector("[id='email-err']")).getText());
        Assertions.assertEquals("You must agree to continue", driver.findElement(By.id("agree-err")).getText());

    }

    private void navigateToFormsPage() {
        WebElement formsMenuButton = driver.findElement(By.cssSelector("a[href='#/forms']"));
        formsMenuButton.click();
    }
    private void clickSubmitButton() {
        WebElement submitButton = null;
        var buttons = driver.findElements(By.tagName("button"));
        for (WebElement button: buttons) {
            if (button.getText().toLowerCase(Locale.ROOT).equals("submit")) {
                submitButton = button;
                break;
            }
        }
        if (submitButton != null) {
            submitButton.click();
        }
        else {
            throw (new NotFoundException());
        }
    }

    @Test
    public void FormsSubmitPopUpTest()
    {
        // arrange - click on forms menu item to navigate to Forms page
        navigateToFormsPage();

        // act - enter some text in required fields and then click submit

        String name = new String("Simon");
        String email = new String("test@email.com");
        driver.findElement(By.cssSelector("input#name")).sendKeys(name);
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        WebElement checkbox = driver.findElement(By.cssSelector("input#agree")); // .sendKeys(Keys.ENTER); //.click();

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", checkbox);

        clickSubmitButton();

        // wait for pop-up to appear

        new WebDriverWait(driver, 10).until(ExpectedConditions.
                visibilityOf(driver.findElement(By.cssSelector("[class='v-snack__content']"))));

        // assert - pop-up message text is correct
        String expected = new String("Thanks for your feedback " + name);
        Assertions.assertEquals(expected, driver.findElement(By.cssSelector("[class='snackbar popup-message mr-auto']")).getText());
//        Assertions.assertEquals(expected, driver.findElement(By.cssSelector("[class='v-snack__content']")).getText());
    }


}
