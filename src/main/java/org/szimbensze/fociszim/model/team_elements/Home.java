package org.szimbensze.fociszim.model.team_elements;

/**
 * Home team has a slight advantage over a Visitor team.
 */
public class Home extends Team {

    public Home() {
        super();
        setMinuteChanceModifier(0.0024F);
        setChanceDecreaseNumber(0.0166F);
        setShotChanceMultiplier(1.47F);
    }

}
