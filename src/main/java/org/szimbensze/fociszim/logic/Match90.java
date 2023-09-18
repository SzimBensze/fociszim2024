package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

public class Match90 extends Match {

    public Match90(Team team1, Team team2) {
        super(team1, team2);
        setFirstMinute(1);
        setLastMinute(91);
        setHalfTime(45);
        setMaxEventAmount(4);
    }

    @Override
    public void initiateMatch(Float defaultChanceMultiplier) throws InterruptedException {
        TextPrinter.printDivider();
        TextPrinter.printInitiation();
        super.initiateMatch(defaultChanceMultiplier);
    }

}
