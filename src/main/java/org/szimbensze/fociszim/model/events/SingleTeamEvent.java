package org.szimbensze.fociszim.model.events;

import org.szimbensze.fociszim.model.team_elements.Team;

public class SingleTeamEvent extends FootballEvent {

    Team affectedTeam;
    Boolean isVar;

    public SingleTeamEvent(EventType type, Boolean isVar, Team affectedTeam) {
        super(type);
        this.affectedTeam = affectedTeam;
        this.isVar = isVar;
    }

}
