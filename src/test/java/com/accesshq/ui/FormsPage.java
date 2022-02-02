package com.accesshq.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormsPage {

    WebDriver driver;

    public FormsPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(By.cssSelector("input#name")).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
    }

    public void clickAgree() {
        driver.findElement(By.cssSelector("[for=agree]")).click();
    }

    public String getPopupMessage() {
        return driver.findElement(By.cssSelector("[class='snackbar popup-message mr-auto']")).getText();
    }

    public String getAcceptErrorMessage() {
        return driver.findElement(By.cssSelector("[id='agree-err']")).getText();
    }

    public String getNameErrorMessage() {
        return driver.findElement(By.cssSelector("[id='name-err']")).getText();
    }

    public String getEmailErrorMessage() {
        return driver.findElement(By.cssSelector("[id='email-err']")).getText();
    }

    public boolean popupIsVisible() {
        return driver.findElement(By.cssSelector("[class='v-snack__content']")).isDisplayed();
    }


    public void clickSubmitButton() {
        WebElement submitButton = null;
        var buttons = driver.findElements(By.tagName("button"));
        for (WebElement button: buttons) {
            if (button.getText().equalsIgnoreCase("submit")) {
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

    private void clickButton(String buttonText) {
        WebElement btn = null;
        var buttons = driver.findElements(By.tagName("button"));
        for (WebElement button: buttons) {
            if (button.getText().equalsIgnoreCase(buttonText)) {
                btn = button;
                break;
            }
        }
        if (btn != null) {
            btn.click();
        }
        else {
            throw (new NotFoundException());
        }
    }
}
