package org.szimbensze.fociszim.model.team_elements;

/**
 * Home team has a slight advantage over a Visitor team.
 */
public class Home extends Team {

    public Home() {
        super();
        setMinuteChanceModifier(0.0022F);
        setChanceDecreaseNumber(0.0171F);
        setShotChanceMultiplier(1.35F);
    }

}
