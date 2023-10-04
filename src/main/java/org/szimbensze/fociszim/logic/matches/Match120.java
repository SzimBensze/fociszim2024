package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.visual.TextPrinter;

public class Match120 extends Match {

    public Match120(Match match) {
        super(match.getTeamOne(), match.getTeamTwo());
        setFirstMinute(91);
        setLastMinute(120);
        setHalfTime(105);
        setFirstHalfMaxStoppageTime(4);
        setSecHalfMaxStoppageTime(5);
        setMaxEventAmount(3);
    }

    @Override
    protected void doHalftime() throws InterruptedException {
        if (teamOne.getShots() < 7 && teamOne.getGoals() == 0) teamOne.setMinuteChance(teamOne.getMinuteChance() + teamOne.getMinuteChanceModifier() * 3F);
        if (teamTwo.getShots() < 7 && teamTwo.getGoals() == 0) teamTwo.setMinuteChance(teamTwo.getMinuteChance() + teamTwo.getMinuteChanceModifier() * 3F);
        TextPrinter.printHalftime(teamOne, teamTwo, "ET Half-time!");
    }

}
