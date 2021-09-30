package com.accesshq.strategies;

import com.accesshq.ui.PlanetTile;

public class RadiusMatch implements MatchingStrategy {

    String radius;

    public RadiusMatch(String radius)
    {
        this.radius = radius;
    }

    @Override
    public boolean match(PlanetTile planet)
    {
        if (planet.getRadius().equalsIgnoreCase(radius)) {
            return true;
        }
        return false;
    }
}
