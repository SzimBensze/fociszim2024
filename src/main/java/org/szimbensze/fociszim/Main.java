package org.szimbensze.fociszim;

import org.szimbensze.fociszim.logic.*;
import org.szimbensze.fociszim.logic.matches.Match120;
import org.szimbensze.fociszim.logic.matches.Match90;
import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;
import org.szimbensze.fociszim.visual.TextPrinter;
import org.szimbensze.fociszim.visual.UserInput;

public class Main {
    public static void main(String[] args) throws IncorrectTeamTypeException, InterruptedException {

        System.out.println("Szim's Football Simulator 2024");
        System.out.println("The program is currently work in progress! There's not much to look at yet, please check back later.");
        TextPrinter.printDivider();

        TeamCreator teamCreator = new TeamCreator();
        ChanceCalculator calculator = new ChanceCalculator(500F);

        Team homeTeam = teamCreator.createTeam(Home.class);
        teamCreator.addStats(homeTeam);
        TextPrinter.printTeam(homeTeam);
        TextPrinter.printDivider();

        Team visitorTeam = teamCreator.createTeam(Visitor.class);
        teamCreator.addStats(visitorTeam);
        TextPrinter.printTeam(visitorTeam);
        TextPrinter.printDivider();

        teamCreator.addBaseChance(homeTeam, calculator.CalcBaseChance(homeTeam, visitorTeam));
        teamCreator.addBaseChance(visitorTeam, calculator.CalcBaseChance(visitorTeam, homeTeam));
        teamCreator.addMinuteChance(homeTeam, calculator.CalcMinuteChance(homeTeam, visitorTeam));
        teamCreator.addMinuteChance(visitorTeam, calculator.CalcMinuteChance(visitorTeam, homeTeam));

        if (UserInput.inputYesNo("Display stats? (y/n)")) {
            System.out.println(homeTeam);
            System.out.println(visitorTeam);
        }

        Match90 baseMatch = new Match90(homeTeam, visitorTeam);
        baseMatch.initiateMatch(1F);
        if (baseMatch.getWinner() == null) if (UserInput.inputYesNo("")) {
            Match120 extraTime = new Match120(homeTeam, visitorTeam);
            extraTime.initiateMatch(1.05F);
        }
        else TextPrinter.printWinner(baseMatch.getWinner());
        //Thread.sleep(Long.MAX_VALUE);
    }
}
