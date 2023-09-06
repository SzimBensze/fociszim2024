package org.szimbensze.fociszim;

import org.szimbensze.fociszim.logic.*;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;
import org.szimbensze.fociszim.visual.TextPrinter;

public class Main {
    public static void main(String[] args) throws IncorrectTeamTypeException {
        TextPrinter printer = new TextPrinter();
        System.out.println("Szim's Football Simulator 2024");
        System.out.println("The program is currently work in progress! There's not much to look at, please check back later.");
        printer.printDivider();

        TeamCreator teamCreator = new TeamCreator();
        ChanceCalculator calculator = new ChanceCalculator(500F);

        Team homeTeam = teamCreator.createTeam(Home.class);
        teamCreator.addStats(homeTeam);
        teamCreator.addFormation(homeTeam);
        printer.printDivider();
        printer.printTeam(homeTeam);
        printer.printDivider();

        Team visitorTeam = teamCreator.createTeam(Visitor.class);
        teamCreator.addStats(visitorTeam);
        teamCreator.addFormation(visitorTeam);
        printer.printDivider();
        printer.printTeam(visitorTeam);
        printer.printDivider();

        teamCreator.addChances(homeTeam,
                calculator.CalcBaseChance(homeTeam, visitorTeam),
                calculator.CalcMinuteChance(homeTeam, visitorTeam));
        teamCreator.addChances(visitorTeam,
                calculator.CalcBaseChance(visitorTeam, homeTeam),
                calculator.CalcMinuteChance(visitorTeam, homeTeam));

        //System.out.println(homeTeam);
    }
}
