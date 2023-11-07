package org.szimbensze.fociszim.model.events;

import org.szimbensze.fociszim.model.team_elements.Team;

import java.util.List;

/**
 * A football event that always includes both teams. List type and order doesn't matter.
 */
public class TwoTeamEvent extends FootballEvent {

    private final List<Team> affectedTeams;

    public TwoTeamEvent(EventType type, List<Team> affectedTeams) {
        super(type);
        this.affectedTeams = affectedTeams;
    }

    public List<Team> getAffectedTeams() {
        return affectedTeams;
    }

}
