package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.logic.ChanceCalculator;
import org.szimbensze.fociszim.logic.TeamCreator;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

public class Match90 extends Match {

    public Match90(Team team1, Team team2, boolean isStat) {
        super(team1, team2, isStat);
        setFirstMinute(1);
        setLastMinute(90);
        setHalfTime(45);
        firstHalfStoppageMinutes.add(8, 0);
        firstHalfStoppageMinutes.add(19, 1);
        firstHalfStoppageMinutes.add(26, 2);
        firstHalfStoppageMinutes.add(31, 3);
        firstHalfStoppageMinutes.add(11, 4);
        firstHalfStoppageMinutes.add(5, 5);
        secHalfStoppageMinutes.add(1, 0);
        secHalfStoppageMinutes.add(11, 1);
        secHalfStoppageMinutes.add(19, 2);
        secHalfStoppageMinutes.add(29, 3);
        secHalfStoppageMinutes.add(22, 4);
        secHalfStoppageMinutes.add(12, 5);
        secHalfStoppageMinutes.add(6, 6);
        maxEventAmount.add(50, 0);
        maxEventAmount.add(23, 1);
        maxEventAmount.add(13, 2);
        maxEventAmount.add(10, 3);
        maxEventAmount.add(4, 4);
    }

    @Override
    public void initiateMatch() throws InterruptedException {
        Thread.sleep(3000);
        TextPrinter.printInitiation();
        super.initiateMatch();
    }

    @Override
    protected void doHalftime() throws InterruptedException {
        if (teamOne.getShots() < 5 && teamOne.getGoals() == 0) teamOne.setMinuteChance(teamOne.getMinuteChance() + teamOne.getMinuteChanceModifier() * 7F);
        if (teamTwo.getShots() < 5 && teamTwo.getGoals() == 0) teamTwo.setMinuteChance(teamTwo.getMinuteChance() + teamTwo.getMinuteChanceModifier() * 7F);
        TextPrinter.printHalftime(teamOne, teamTwo, "Half-time!");
    }

    /**
     * Initiates the team creator method and creates two teams. Must run in order to play a simulation.
     */
    public void createTeams() {
        TeamCreator teamCreator = new TeamCreator();
        ChanceCalculator calculator = new ChanceCalculator(500F);

        teamCreator.addStats(teamOne);
        TextPrinter.printTeam(teamOne);
        TextPrinter.printDivider();

        teamCreator.addStats(teamTwo);
        TextPrinter.printTeam(teamTwo);
        TextPrinter.printDivider();

        teamCreator.addBaseChance(teamOne, calculator.CalcBaseChance(teamOne, teamTwo));
        teamCreator.addBaseChance(teamTwo, calculator.CalcBaseChance(teamTwo, teamOne));
        teamCreator.addMinuteChance(teamOne, calculator.CalcMinuteChance(teamOne, teamTwo));
        teamCreator.addMinuteChance(teamTwo, calculator.CalcMinuteChance(teamTwo, teamOne));
    }

}
