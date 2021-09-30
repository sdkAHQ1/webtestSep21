package com.accesshq.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlanetTile {

    private WebDriver driver;

    public PlanetTile(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getPlanetTile(String planetName) {
        var planetTiles = driver.findElements(By.className("planet"));
        for (WebElement tile : planetTiles) {
            var thisElement = tile.findElement(By.cssSelector("h2"));
            if (thisElement.getText().equalsIgnoreCase(planetName)) {
                return tile;
            }
        }
        throw new NotFoundException("planet tile not found");
    }

    public String getDistance(String planet) {
        return getPlanetTile(planet).findElement(By.className("distance")).getText();
    }
}
