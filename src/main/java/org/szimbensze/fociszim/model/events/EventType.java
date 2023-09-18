package org.szimbensze.fociszim.model.events;

public enum EventType {

    PENALTY(0F, false, true),
    YELLOW_CARD(-0.002F, false, true),
    RED_CARD(-0.018F, false, true),
    VAR_GOAL(0F, false, true),
    GOAL_DENIED(-0.001F, false, true),
    INJURY(-0.01F, false, false),
    OBSTRUCTION(-0.001F, true, false),
    INVADER(0.001F, true, false),
    NOT_FOUL(0.002F, false, false);

    public final Float chanceModifier;
    public final boolean affectBothTeams;
    public final boolean canBeVar;

    EventType(Float chanceModifier, boolean affectBothTeams, boolean canBeVar) {
        this.chanceModifier = chanceModifier;
        this.affectBothTeams = affectBothTeams;
        this.canBeVar = canBeVar;
    }
}
