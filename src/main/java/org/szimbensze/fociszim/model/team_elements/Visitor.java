package org.szimbensze.fociszim.model.team_elements;

/**
 * Visitor team has slightly worse chances than a Home team.
 */
public class Visitor extends Team {

    public Visitor() {
        super();
        setMinuteChanceModifier(0.0022F);
        setChanceDecreaseNumber(0.0184F);
        setShotChanceMultiplier(1.43F);
    }

}
