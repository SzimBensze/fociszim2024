package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Team;

public class Match90 extends Match {

    public Match90(Team team1, Team team2) {
        super(team1, team2);
        setFirstMinute(1);
        setLastMinute(91);
        setMaxEventAmount(4);
    }

}
