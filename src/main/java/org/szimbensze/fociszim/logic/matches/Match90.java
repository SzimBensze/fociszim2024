package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.logic.ChanceCalculator;
import org.szimbensze.fociszim.logic.TeamCreator;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

public class Match90 extends Match {

    public Match90(Team team1, Team team2) {
        super(team1, team2);
        setFirstMinute(1);
        setLastMinute(90);
        setHalfTime(45);
        setMaxEventAmount(4);
    }

    @Override
    public void initiateMatch(Float defaultChanceMultiplier) throws InterruptedException {
        TextPrinter.printDivider();
        TextPrinter.printInitiation();
        super.initiateMatch(defaultChanceMultiplier);
    }

    @Override
    protected void doHalftime() throws InterruptedException {
        if (teamOne.getShots() < 5 && teamOne.getGoals() == 0) teamOne.setMinuteChance(teamOne.getMinuteChance() + teamOne.getMinuteChanceModifier() * 2F);
        if (teamTwo.getShots() < 5 && teamTwo.getGoals() == 0) teamTwo.setMinuteChance(teamTwo.getMinuteChance() + teamTwo.getMinuteChanceModifier() * 2F);
        TextPrinter.printHalftime(teamOne, teamTwo, "Half-time!");
    }

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
