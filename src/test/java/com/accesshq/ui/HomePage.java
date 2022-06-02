package com.accesshq.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void navigateToFormsPage() {
        WebElement menuButton = driver.findElement(By.cssSelector("a[href='#/forms']"));
        menuButton.click();
    }

    public void navigateToPlanetsPage() {
        WebElement menuButton = driver.findElement(By.cssSelector("a[href='#/planets']"));
        menuButton.click();
    }

    public String getHomePageHeading()
    {
        return driver.findElement(By.cssSelector("h1.display-1")).getText();
    }

    public List<WebElement> getTableElements() {
        var tableElements = driver.findElement(By.tagName("table"));
        List<WebElement> inputElements = tableElements.findElements(By.tagName("input"));
        return inputElements;
    }
}
