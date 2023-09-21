package org.szimbensze.fociszim.visual;

import org.szimbensze.fociszim.model.events.IncorrectEventTypeException;
import org.szimbensze.fociszim.model.events.SingleTeamEvent;
import org.szimbensze.fociszim.model.events.TwoTeamEvent;
import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;

import java.util.stream.IntStream;

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

    public static void printSingleEvent(SingleTeamEvent event) throws InterruptedException, IncorrectEventTypeException {
        try {
            if (event.getVar()) {
                System.out.println("[VAR] Investigating incident");
                Thread.sleep(2000);
                printDots(5);
            }
        }
        catch (NullPointerException ex) {
            System.out.println("Event incorrect variables!");
            Thread.sleep(500);
            System.exit(1);
        }
        switch (event.getType()) {
            case PENALTY -> {
                System.out.printf("%s got a penalty kick!%n", event.getAffectedTeam().getName());
                Thread.sleep(2000);
                printDots(3);
            }
            case YELLOW_CARD -> {
                System.out.printf("%s got a yellow card!%n", event.getAffectedTeam().getName());
                Thread.sleep(1000);
            }
            case RED_CARD -> {
                System.out.printf("%s got a red card!%n", event.getAffectedTeam().getName());
                Thread.sleep(1000);
            }
            case VAR_GOAL -> {
                if (!event.getVar()) {
                    System.out.println("[GLT] Investigating validity");
                    Thread.sleep(2000);
                    printDots(5);
                }
            }
            case INJURY -> {
                System.out.printf("%s got an injured player!%n", event.getAffectedTeam().getName());
                Thread.sleep(2000);
                System.out.println("Player is being taken care of");
                printDots(5);
            }
            case NOT_FOUL -> {
                if (!event.getVar()) {
                    System.out.println("[REF] Investigating foul");
                    Thread.sleep(2000);
                    printDots(5);
                }
                System.out.printf("%s did not commit a foul! %n", event.getAffectedTeam().getName());
            }
            default -> throw new IncorrectEventTypeException("Incorrect event type!");
        }
        Thread.sleep(1000);
    }

    public static void printDuoEvent(TwoTeamEvent event) throws InterruptedException, IncorrectEventTypeException {
        switch (event.getType()) {
            case OBSTRUCTION -> {
                //TODO
            }
            case INVADER -> {
                //TODO
            }
            default -> throw new IncorrectEventTypeException("Incorrect event type!");
        }
    }

    private static void printDots(int num) throws InterruptedException {
        for (int i = 1; i <= num; i++) {
            IntStream.range(0, i).forEach(j -> System.out.print("."));
            System.out.println();
            Thread.sleep(500);
        }
    }

    public static void printHalftime(Team teamOne, Team teamTwo, String halftimeMessage) throws InterruptedException {
        System.out.println(halftimeMessage);
        printGoalStats(teamOne, teamTwo);
        Thread.sleep(2000);
    }

    public static void printWinner(Team winner) {
        if (winner == null) System.out.println("The match is a tie! Congratulations to both teams.");
        else System.out.printf("%s won the match! Congratulations!\n", winner.getName());
    }

    public static void printGoalStats(Team leftTeam, Team rightTeam) {
        System.out.println("-----");
        System.out.println("Match statistics:");
        System.out.printf("%s - %s Goals: %s - %s Shots: %s - %s Accuracy: %s%% - %s%%%n",
                leftTeam.getName(), rightTeam.getName(),
                leftTeam.getGoals(), rightTeam.getGoals(),
                leftTeam.getShots(), rightTeam.getShots(),
                String.format("%.2f", leftTeam.getAccuracy()),
                String.format("%.2f", rightTeam.getAccuracy()));
    }

}
