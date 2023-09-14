package org.szimbensze.fociszim.model;

public class SingleTeamEvent extends FootballEvent {

    Team affectedTeam;
    Boolean isVar;

    public SingleTeamEvent(EventType type, Boolean isVar, Team affectedTeam) {
        super(type);
        this.affectedTeam = affectedTeam;
        this.isVar = isVar;
    }

}
