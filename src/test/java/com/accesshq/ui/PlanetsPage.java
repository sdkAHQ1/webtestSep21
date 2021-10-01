package com.accesshq.ui;

import com.accesshq.strategies.MatchingStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Predicate;

public class PlanetsPage {

    WebDriver driver;

    public PlanetsPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public PlanetTile getPlanetTile(MatchingStrategy strategy) {
        var planetTiles = driver.findElements(By.className("planet"));
        for (WebElement tile : planetTiles) {
            PlanetTile planet = new PlanetTile(tile);
            if (strategy.match(planet)) {
                return planet;
            }
        }
        throw new NotFoundException("planet not found");
    }

    public PlanetTile getPlanetTilePred(Predicate<PlanetTile> predicate) {
        var planetTiles = driver.findElements(By.className("planet"));
        for (WebElement tile : planetTiles) {
            PlanetTile planet = new PlanetTile(tile);
            if (predicate.test(planet)) {
                return planet;
            }
        }
        throw new NotFoundException("planet not found");
    }

}
