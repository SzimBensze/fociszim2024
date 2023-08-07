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
        if (team.getFormation() == null) System.out.printf("%s formation not selected!%n", team.getName());
        else System.out.printf("%s formation: %s%n", team.getName(), team.getFormation().formationName());
    }

    public void printDivider() {
        System.out.println();
        System.out.println("-----");
        System.out.println();
    }

}
