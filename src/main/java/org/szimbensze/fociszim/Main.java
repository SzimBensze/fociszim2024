package org.szimbensze.fociszim;

import org.szimbensze.fociszim.logic.ChanceCalculator;
import org.szimbensze.fociszim.logic.IncorrectTeamTypeException;
import org.szimbensze.fociszim.logic.Match90;
import org.szimbensze.fociszim.logic.TeamCreator;
import org.szimbensze.fociszim.model.Formation;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;
import org.szimbensze.fociszim.visual.TextPrinter;

public class Main {
    public static void main(String[] args) throws IncorrectTeamTypeException {
        System.out.println("Szim's Football Simulator 2024");
        System.out.println("The program is currently work in progress! There's not much to look at, please check back later.");

        Team homeTeam = TeamCreator.createTeam(Home.class);
        Team visitorTeam = TeamCreator.createTeam(Visitor.class);
        ChanceCalculator calculator = new ChanceCalculator();
        Match90 baseMatch = new Match90();
        TextPrinter printer = new TextPrinter();

        TeamCreator.addStats(homeTeam, "AFK", 42, 39, 39);
        TeamCreator.addFormation(homeTeam, Formation.F4141);
        TeamCreator.addStats(visitorTeam, "ZOL", 44, 45, 44);
        TeamCreator.addFormation(visitorTeam, Formation.F4411);

        printer.printTeam(homeTeam);
        printer.printTeam(visitorTeam);
    }
}
