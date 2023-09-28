package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;

public class MatchBuilder {

    public static Match90 createBaseMatch(LocationType location, boolean isStat) throws InterruptedException {
        Team teamOne = location.equals(LocationType.HOME_STADIUM) ? new Home() : new Visitor();
        Team teamTwo = new Visitor();

        Match90 baseMatch = new Match90(teamOne, teamTwo);
        baseMatch.createTeams();
        return baseMatch;
    }

    public static Match120 createExtraMatch(Match previousMatch) {
        return new Match120(previousMatch);
    }

}
