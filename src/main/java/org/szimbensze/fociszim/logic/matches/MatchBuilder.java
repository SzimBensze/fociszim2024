package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.logic.TeamCreator;
import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;

public class MatchFactory {

    public static Match createMatch(MatchType type, LocationType location, boolean isStat) {
        TeamCreator teamCreator = new TeamCreator();
        Team homeTeam = teamCreator.createTeam(Home.class);
        Team visitorTeam = teamCreator.createTeam(Visitor.class);

        Match90 baseMatch = new Match90(homeTeam, visitorTeam);
        baseMatch.createTeams();
    }

}
