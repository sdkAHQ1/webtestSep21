package com.accesshq.strategies;

import com.accesshq.ui.PlanetTile;

public class NameMatch implements MatchingStrategy {

    private String name;

    public NameMatch(String name)
    {
        this.name = name;
    }

    @Override
    public boolean match(PlanetTile planetTile)
    {
        if (planetTile.getName().equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }
}
