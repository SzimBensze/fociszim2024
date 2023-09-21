package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.logic.ChanceCalculator;
import org.szimbensze.fociszim.logic.TeamCreator;
import org.szimbensze.fociszim.model.team_elements.Team;
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
