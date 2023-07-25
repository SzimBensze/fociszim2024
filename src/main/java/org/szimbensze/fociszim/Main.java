package org.szimbensze.fociszim;

import org.szimbensze.fociszim.logic.*;
import org.szimbensze.fociszim.model.Formation;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;
import org.szimbensze.fociszim.visual.TextPrinter;

public class Main {
    public static void main(String[] args) throws IncorrectTeamTypeException {
        System.out.println("Szim's Football Simulator 2024");
        System.out.println("The program is currently work in progress! There's not much to look at, please check back later.");

        TeamCreator teamCreator = new TeamCreator();
        ChanceCalculator calculator = new ChanceCalculator();
        Match90 baseMatch = new Match90();
        TextPrinter printer = new TextPrinter();

        Team homeTeam = teamCreator.createTeam(Home.class);
        Team visitorTeam = teamCreator.createTeam(Visitor.class);

        teamCreator.addStats(homeTeam);
        teamCreator.addFormation(homeTeam, Formation.F4141);
        printer.printTeam(homeTeam);

        teamCreator.addStats(visitorTeam);
        teamCreator.addFormation(visitorTeam, Formation.F4411);
        printer.printTeam(visitorTeam);
    }
}
