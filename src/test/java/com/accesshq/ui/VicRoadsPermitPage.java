package com.accesshq.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VicRoadsPermitPage {

    private WebDriver driver;

    public VicRoadsPermitPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public boolean isHeader(String heading) {
        WebElement pageMain = driver.findElement(By.cssSelector("#main"));
        WebElement header = pageMain.findElement(By.tagName("h1"));
        return header.getText().toLowerCase().contains(heading);
    }

    public boolean isCreditCardFormAccessible() {
        WebElement creditCardForm = driver.findElement(By.cssSelector("[id=creditCardForm]"));
        return creditCardForm.isDisplayed();
    }
}
