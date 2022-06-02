package com.accesshq.strategies;

import com.accesshq.ui.PlanetTile;

public class RadiusMatch implements MatchingStrategy {

    private String radius;

    public RadiusMatch(String radius)
    {
        this.radius = radius;
    }

    @Override
    public boolean match(PlanetTile planetTile)
    {
        if (planetTile.getRadius().equalsIgnoreCase(radius)) {
            return true;
        }
        return false;
    }
}
