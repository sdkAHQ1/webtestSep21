package com.accesshq.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PlanetTile {

    private WebElement thisElement;

    public PlanetTile(WebElement ele) {
        this.thisElement = ele;
    }

    public String getName() {
        return thisElement.findElement(By.className("name")).getText();
    }

    public String getDistance() {
        return thisElement.findElement(By.className("distance")).getText();
    }

    public double getDistanceAsNumber() {
        String strD = thisElement.findElement(By.className("distance")).getText();
        String distance = strD.substring(0, strD.length() - 3).replaceAll(",","");
        return Double.parseDouble(distance);
    }

    public String getRadius() {
        return thisElement.findElement(By.className("radius")).getText();
    }


}
