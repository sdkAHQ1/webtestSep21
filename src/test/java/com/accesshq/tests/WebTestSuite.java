package com.accesshq.tests;

import com.accesshq.strategies.NameMatch;
import com.accesshq.strategies.RadiusMatch;
import com.accesshq.ui.FormsPage;
import com.accesshq.ui.HomePage;

import com.accesshq.ui.PlanetTile;
import com.accesshq.ui.PlanetsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WebTestSuite {

    private WebDriver driver;

    @BeforeEach
    public void Setup()
    {
        driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://d18u5zoaatmpxx.cloudfront.net/#/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void Cleanup()
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
        var headingText = new HomePage(driver).getHomePageHeading();

        // assert
        Assertions.assertEquals("Web Playground", headingText);
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
        var homePage = new HomePage(driver);

        // act
        List<WebElement> tableInputElements = homePage.getTableElements();

        // assert
        Assertions.assertEquals(1, Integer.parseInt(tableInputElements.get(0).getAttribute("value")));

    }


    @Test
    public void JupiterDistanceTest()
    {
        // arrange - click on planets menu item to navigate to planets page
        new HomePage(driver).navigateToPlanetsPage();
        var planetsPage = new PlanetsPage(driver);

        // act - find planet tile by name
        PlanetTile planet = planetsPage.getPlanetTile(new NameMatch("Jupiter"));

        // lambda predicate
        PlanetTile planet2 = planetsPage.getPlanetTilePred(planetTile -> planetTile.getName().contains("Jupiter"));

        // assert - distance to jupiter is
        Assertions.assertEquals("778,500,000 km", planet.getDistance());
        Assertions.assertEquals("778,500,000 km", planet2.getDistance());

        // act - find planet tile by radius
        planet = planetsPage.getPlanetTile(new RadiusMatch("69,911 km"));

        // lambda predicate
        planet2 = planetsPage.getPlanetTilePred(planetTile -> planetTile.getRadius().contains("69,911 km"));

        // assert - name is jupiter
        Assertions.assertEquals("Jupiter", planet.getName());
        Assertions.assertEquals("Jupiter", planet2.getName());
    }

    @Test
    public void NeptuneFurthestDistanceTest()
    {
        // arrange - click on planets menu item to navigate to planets page
        new HomePage(driver).navigateToPlanetsPage();
        var planetsPage = new PlanetsPage(driver);

        // act - get the distance of all planets
        double max = 0;
        double distance = 0;
        var planetTiles = driver.findElements(By.className("planet"));
        for (WebElement tile : planetTiles) {
            PlanetTile planet = new PlanetTile(tile);
            distance = planet.getDistanceAsNumber();
            if (distance > max)
            {
                max = distance;
            }
        }

        double furthest = max;
        PlanetTile furthestPlanet = planetsPage.getPlanetTilePred(
                planetTile -> planetTile.getDistanceAsNumber()== furthest);

        // assert that planet with furthest distance is neptune
        Assertions.assertEquals("Neptune", furthestPlanet.getName());

    }


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
