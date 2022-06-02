package com.accesshq.tests;

import com.accesshq.ui.FormsPage;
import com.accesshq.ui.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebTests extends BaseTestSuite {

    @Test
    public void FormsErrorMsgTest()
    {
        // arrange - click on forms menu item to navigate to Forms page
        new HomePage(driver).navigateToFormsPage();
        var formsPage = new FormsPage(driver);
        formsPage.clickSubmitButton();
        // assert - error messages are correct
        Assertions.assertEquals("Your name is required", formsPage.getNameErrorMessage());
        Assertions.assertEquals("Your email is required",formsPage.getEmailErrorMessage());
        Assertions.assertEquals("You must agree to continue", formsPage.getAcceptErrorMessage());
    }

    @Test
    public void FormsSubmitPopUpTest()
    {
        // arrange - click on forms menu item to navigate to Forms page
        new HomePage(driver).navigateToFormsPage();
        var formsPage = new FormsPage(driver);

        // act - enter some text in required fields and then click submit
        String name = "Simon";
        formsPage.setName(name);
        formsPage.setEmail("test@email.com");
        formsPage.clickAgree();
        formsPage.clickSubmitButton();

        // wait for pop-up to appear
        new WebDriverWait(driver, 10).until(d -> formsPage.popupIsVisible());

        // assert - pop-up message text is correct
        String expected = "Thanks for your feedback " + name;
        Assertions.assertEquals(expected, formsPage.getPopupMessage());
    }

}
