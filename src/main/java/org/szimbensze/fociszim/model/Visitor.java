package org.szimbensze.fociszim.model;

public class Visitor extends Team {

    @Override
    public void setMinuteChanceModifier(Float minuteChanceModifier) {
        minuteChanceModifier = Float.valueOf("0.015");
    }

}
