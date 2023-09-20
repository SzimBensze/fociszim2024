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

    public static void printHalftime(Team teamOne, Team teamTwo) throws InterruptedException {
        System.out.println("Half-time!");
        printGoalStats(teamOne, teamTwo);
        Thread.sleep(3000);
    }

    public static void printWinner(Team winner) {
        if (winner == null) System.out.println("The match is a tie! Congratulations to both teams.");
        else System.out.printf("%s won the match! Congratulations!", winner.getName());
    }

    public static void printGoalStats(Team leftTeam, Team rightTeam) throws InterruptedException {
        System.out.println("-----");
        System.out.println("Match statistics:");
        System.out.printf("%s - %s Goals: %s - %s Shots: %s - %s Accuracy: %s%% - %s%%%n",
                leftTeam.getName(), rightTeam.getName(),
                leftTeam.getGoals(), rightTeam.getGoals(),
                leftTeam.getShots(), rightTeam.getShots(),
                Math.round(leftTeam.getAccuracy()),
                Math.round(rightTeam.getAccuracy()));
        Thread.sleep(3000);
    }

}
