package org.szimbensze.fociszim.model.events;

import org.szimbensze.fociszim.model.team_elements.Team;

import java.util.List;

public class TwoTeamEvent extends FootballEvent {

    List<Team> affectedTeams;

    public TwoTeamEvent(EventType type, List<Team> affectedTeams) {
        super(type);
        this.affectedTeams = affectedTeams;
    }

}
