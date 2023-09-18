package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Team;

public class Match120 extends Match {

    public Match120(Team team1, Team team2) {
        super(team1, team2);
        setFirstMinute(91);
        setLastMinute(121);
        setHalfTime(105);
        setMaxEventAmount(3);
    }

}
