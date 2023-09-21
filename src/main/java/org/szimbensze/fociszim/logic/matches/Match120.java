package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

public class Match120 extends Match {

    public Match120(Team team1, Team team2) {
        super(team1, team2);
        setFirstMinute(91);
        setLastMinute(121);
        setHalfTime(105);
        setMaxEventAmount(3);
    }

    @Override
    protected void doHalftime() throws InterruptedException {
        if (teamOne.getShots() < 7 && teamOne.getGoals() == 0) teamOne.setMinuteChance(teamOne.getMinuteChance() + teamOne.getMinuteChanceModifier() * 3F);
        if (teamTwo.getShots() < 7 && teamTwo.getGoals() == 0) teamTwo.setMinuteChance(teamTwo.getMinuteChance() + teamTwo.getMinuteChanceModifier() * 3F);
        TextPrinter.printHalftime(teamOne, teamTwo, "ET Half-time!");
    }
}
