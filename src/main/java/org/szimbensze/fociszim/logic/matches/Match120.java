package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.visual.TextPrinter;

public class Match120 extends Match {

    public Match120(Match prevMatch) {
        super(prevMatch.teamOne, prevMatch.teamTwo, prevMatch.isStatDisplay);
        setFirstMinute(91);
        setLastMinute(120);
        setHalfTime(105);
        firstHalfStoppageMinutes.add(20, 0);
        firstHalfStoppageMinutes.add(30, 1);
        firstHalfStoppageMinutes.add(36, 2);
        firstHalfStoppageMinutes.add(14, 3);
        secHalfStoppageMinutes.add(13, 0);
        secHalfStoppageMinutes.add(25, 1);
        secHalfStoppageMinutes.add(29, 2);
        secHalfStoppageMinutes.add(27, 3);
        secHalfStoppageMinutes.add(6, 4);
        setMaxEventAmount(3);
    }

    @Override
    public void initiateMatch() throws InterruptedException {
        Thread.sleep(1000);
        TextPrinter.printDivider();
        super.initiateMatch();
    }

    @Override
    protected void doHalftime() throws InterruptedException {
        if (teamOne.getShots() < 7 && teamOne.getGoals() == 0) teamOne.setMinuteChance(teamOne.getMinuteChance() + teamOne.getMinuteChanceModifier() * 3F);
        if (teamTwo.getShots() < 7 && teamTwo.getGoals() == 0) teamTwo.setMinuteChance(teamTwo.getMinuteChance() + teamTwo.getMinuteChanceModifier() * 3F);
        TextPrinter.printHalftime(teamOne, teamTwo, "ET Half-time!");
    }

}
