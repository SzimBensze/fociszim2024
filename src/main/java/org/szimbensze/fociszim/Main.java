package org.szimbensze.fociszim;

import org.szimbensze.fociszim.logic.*;
import org.szimbensze.fociszim.logic.matches.*;
import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;
import org.szimbensze.fociszim.visual.*;

public class Main {
    public static void main(String[] args) throws IncorrectTeamTypeException, InterruptedException {

        System.out.println("Szim's Football Simulator 2024");
        System.out.println("The program is currently work in progress! There's not much to look at yet, please check back later.");
        TextPrinter.printDivider();

        TeamCreator teamCreator = new TeamCreator();

        Team homeTeam = teamCreator.createTeam(Home.class);
        Team visitorTeam = teamCreator.createTeam(Visitor.class);

        Match90 baseMatch = new Match90(homeTeam, visitorTeam);
        baseMatch.createTeams();
        if (UserInput.inputYesNo("Display stats? (y/n)")) {
            System.out.println(homeTeam);
            System.out.println(visitorTeam);
        }
        baseMatch.initiateMatch(1F);
        if (baseMatch.getWinner() == null) if (UserInput.inputYesNo("Continue with overtime? (y/n)")) {
            Match120 extraTime = new Match120(homeTeam, visitorTeam);
            extraTime.initiateMatch(1.05F);
            TextPrinter.printWinner(extraTime.getWinner());
        }
        else TextPrinter.printWinner(baseMatch.getWinner());
        //Thread.sleep(Long.MAX_VALUE);
    }
}
