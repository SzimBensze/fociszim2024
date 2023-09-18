package org.szimbensze.fociszim.visual;

import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;

public class TextPrinter {

    public static void printTeam(Team team) {
        System.out.println("\nTeam input stats:");
        if (team.getClass().equals(Home.class)) System.out.printf("Home team: %s%n", team.getName());
        else if (team.getClass().equals(Visitor.class)) System.out.printf("Visitor team: %s%n", team.getName());
        System.out.println("Stats: ATK - MID - DEF");
        System.out.printf("%s: %s - %s - %s%n", team.getName(), team.getAtk(), team.getMid(), team.getDef());
        if (team.getFormation() == null) System.out.printf("%s formation not selected!%n", team.getName());
        else System.out.printf("%s formation: %s%n", team.getName(), team.getFormation().formationName());
    }

    public static void printDivider() {
        System.out.println();
        System.out.println("-----");
        System.out.println();
    }

    public static void printInitiation() throws InterruptedException {
        System.out.println("Simulation initiated!");
        Thread.sleep(1300);
        System.out.println("Preparing the field...");
        Thread.sleep(1400);
        System.out.println("The players are warming up...");
        Thread.sleep(1500);
        System.out.println("Kickoff!");
        Thread.sleep(700);
    }

    public static void printRound(Integer minute) {
        System.out.printf("Minute: %s'%n", minute);
    }

    public static void printInterrupted() throws InterruptedException {
        System.out.println("The match was cancelled by console.");
        Thread.sleep(2000);
        System.exit(0);
    }

    public static void printShot(Team team, boolean shot) {
        if (shot) System.out.printf("%s GOOOAAAL!!!%n", team.getName());
        else System.out.printf("%s missed shot%n", team.getName());
    }

    public static void printHalftime() throws InterruptedException {
        System.out.println("Half-time!");
        System.out.println("TODO PRINT HALFTIME STATS");
        Thread.sleep(3000);
    }

}
