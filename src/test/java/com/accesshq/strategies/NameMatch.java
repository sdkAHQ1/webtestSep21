package com.accesshq.strategies;

import com.accesshq.ui.PlanetTile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NameMatch implements MatchingStrategy {

    String name;

    public NameMatch(String name)
    {
        this.name = name;
    }

    @Override
    public boolean match(PlanetTile planet)
    {
        if (planet.getName().equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }
}
