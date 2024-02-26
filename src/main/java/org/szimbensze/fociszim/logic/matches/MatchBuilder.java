package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;

public class MatchBuilder {

    /**
     * Creates a match with a set of two teams. This must be run first in order to create further match objects.
     * @param location Location type enum. Check class file for more details.
     * @param isStat If true, stats and RNG values will be displayed.
     * @return Returns a 90-minutes-long match with two newly created teams.
     */
    public static Match90 createBaseMatch(LocationType location, boolean isStat) {
        Team teamOne = location.equals(LocationType.HOME_STADIUM) ? new Home() : new Visitor();
        Team teamTwo = new Visitor();

        Match90 baseMatch = new Match90(teamOne, teamTwo, isStat);
        baseMatch.createTeams();
        return baseMatch;
    }

    /**
     * Creates a match object of extra time.
     * @param previousMatch A match object that contains the teams and results.
     * @return Returns an extra time match object with two sets of 15 minutes. Minutes are from 90 to 120.
     */
    public static Match120 createExtraMatch(Match previousMatch) {
        return new Match120(previousMatch);
    }

    /**
     * Creates a match object of penalties.
     * @param previousMatch A match object that contains the teams and results.
     * @return Returns a match object which contains a penalty shootout and its methods.
     */
    public static PenaltyShootout createPenaltyShootout(Match previousMatch) {
        return new PenaltyShootout(previousMatch);
    }

}
