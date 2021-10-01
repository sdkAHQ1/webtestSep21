package com.accesshq.strategies;

import com.accesshq.ui.PlanetTile;

public class DistanceMatch implements MatchingStrategy {

    double distance;

    public DistanceMatch(double distance)
    {
        this.distance = this.distance;
    }

    @Override
    public boolean match(PlanetTile planet)
    {
        if (planet.getDistanceAsNumber() == distance) {
            return true;
        }
        return false;
    }
}
