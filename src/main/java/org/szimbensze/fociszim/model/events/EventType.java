package org.szimbensze.fociszim.model.events;

/**
 * An enum that identifies the team's type. It can be a single team event where only one of the teams is affected or duo team event which includes both teams. It can grant a penalty, a goal and increase or decrease the teams' minute chance.
 */
public enum EventType {

    /**
     * Penalty event grants the chosen team a penalty kick. It doesn't modify any chance, but the attempt counts as a shot regardless of goal.
     */
    PENALTY(0F, false, true),
    YELLOW_CARD(-0.002F, false, false),
    RED_CARD(-0.022F, false, true),
    /**
     * This event grants the chosen team a goal verified by VAR. Because of this, the minute chance of the chosen team is slightly decreased.
     */
    VAR_GOAL(-0.005F, false, true),
    /**
     * This event creates a fake goal for the chosen team that is immediately denied afterwards. This decreases the minute chance of the team.
     */
    GOAL_DENIED(-0.001F, false, true),
    INJURY(-0.02F, false, false),
    OBSTRUCTION(-0.001F, true, false),
    INVADER(0.002F, true, false),
    NOT_FOUL(0.002F, false, true),
    /**
     * This event grants a team a goal and decreases the chance for the other one (who caused the own goal).
     */
    OWN_GOAL(-0.005F, true, false);

    public final Float chanceModifier;
    public final boolean affectBothTeams;
    public final boolean canBeVar;

    EventType(Float chanceModifier, boolean affectBothTeams, boolean canBeVar) {
        this.chanceModifier = chanceModifier;
        this.affectBothTeams = affectBothTeams;
        this.canBeVar = canBeVar;
    }
}
