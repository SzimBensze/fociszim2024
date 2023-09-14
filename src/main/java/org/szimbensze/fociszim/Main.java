package org.szimbensze.fociszim;

import org.szimbensze.fociszim.logic.*;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;
import org.szimbensze.fociszim.visual.TextPrinter;
import org.szimbensze.fociszim.visual.UserInput;

public class Main {
    public static void main(String[] args) throws IncorrectTeamTypeException {
        TextPrinter printer = new TextPrinter();
        System.out.println("Szim's Football Simulator 2024");
        System.out.println("The program is currently work in progress! There's not much to look at yet, please check back later.");
        printer.printDivider();

        TeamCreator teamCreator = new TeamCreator();
        ChanceCalculator calculator = new ChanceCalculator(500F);

        Team homeTeam = teamCreator.createTeam(Home.class);
        teamCreator.addStats(homeTeam);
        printer.printDivider();
        printer.printTeam(homeTeam);
        printer.printDivider();

        Team visitorTeam = teamCreator.createTeam(Visitor.class);
        teamCreator.addStats(visitorTeam);
        printer.printDivider();
        printer.printTeam(visitorTeam);
        printer.printDivider();

        teamCreator.addBaseChance(homeTeam, calculator.CalcBaseChance(homeTeam, visitorTeam));
        teamCreator.addBaseChance(visitorTeam, calculator.CalcBaseChance(visitorTeam, homeTeam));
        teamCreator.addMinuteChance(homeTeam, calculator.CalcMinuteChance(homeTeam, visitorTeam));
        teamCreator.addMinuteChance(visitorTeam, calculator.CalcMinuteChance(visitorTeam, homeTeam));

        if (UserInput.inputYesNo("Display stats? (y/n)")) {
            System.out.println(homeTeam);
            System.out.println(visitorTeam);
        }

        Match90 baseMatch = new Match90(homeTeam, visitorTeam);
        baseMatch.initiateMatch();
        System.out.println(baseMatch);

    }
}
