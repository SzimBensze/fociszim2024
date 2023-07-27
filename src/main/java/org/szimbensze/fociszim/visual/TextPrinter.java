package org.szimbensze.fociszim.visual;

import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;

public class TextPrinter {

    public void printTeam(Team team) {
        if (team.getClass().equals(Home.class)) System.out.printf("Home team: %s%n", team.getName());
        else if (team.getClass().equals(Visitor.class)) System.out.printf("Visitor team: %s%n", team.getName());
        System.out.println("Stats: ATK - MID - DEF");
        System.out.printf("%s: %s - %s - %s%n", team.getName(), team.getAtk(), team.getMid(), team.getDef());
    }

    public void printFormation(Team team) {
        if (team.getFormation() == null) System.out.printf("%s formation not selected!");
        else System.out.printf("%s formation: %s%n", team.getName(), team.getFormation().inputString);
    }

    public void printDivider() {
        System.out.println("-----");
        System.out.println();
    }

}
