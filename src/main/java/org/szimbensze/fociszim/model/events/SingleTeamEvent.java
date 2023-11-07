package org.szimbensze.fociszim.model.events;

import org.szimbensze.fociszim.model.team_elements.Team;

/**
 * A football event which only affects one of the teams.
 */
public class SingleTeamEvent extends FootballEvent {

    private final Team affectedTeam;
    private final boolean isVar;

    public SingleTeamEvent(EventType type, Boolean isVar, Team affectedTeam) {
        super(type);
        this.affectedTeam = affectedTeam;
        this.isVar = isVar;
    }

    public Team getAffectedTeam() {
        return affectedTeam;
    }

    public boolean getVar() {
        return isVar;
    }

}
