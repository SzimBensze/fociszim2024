package org.szimbensze.fociszim.model;

import java.util.List;

public class FootballEvent {

    EventType type;
    List<Team> includedTeams;

    public FootballEvent(List<Team> includedTeams, EventType type) {
        this.type = type;
        this.includedTeams = includedTeams;
    }

    @Override
    public String toString() {
        return "FootballEvent{" +
                "type=" + type +
                ", includedTeams=" + includedTeams.toString() +
                '}';
    }
}
