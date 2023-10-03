package org.szimbensze.fociszim.visual;

import org.szimbensze.fociszim.model.events.EventType;
import org.szimbensze.fociszim.model.events.IncorrectEventTypeException;
import org.szimbensze.fociszim.model.events.SingleTeamEvent;
import org.szimbensze.fociszim.model.events.TwoTeamEvent;
import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;

import java.util.Random;
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

    public static void printMinute(Integer minute) {
        System.out.printf("Minute: %s'%n", minute);
    }

    public static void printMinute(Integer minute, Integer addedMinute) {
        System.out.printf("Minute: %s + %s'%n", minute, addedMinute);
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
        if (event.getType().equals(EventType.GOAL_DENIED)) {
            printShot(event.getAffectedTeam(), true);
            Thread.sleep(1000);
        }
        try {
            if (event.getVar()) {
                System.out.printf("[VAR] %s Investigating incident%n", event.getAffectedTeam().getName());
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
                    System.out.printf("[GLT] %s Investigating validity%n", event.getAffectedTeam().getName());
                    Thread.sleep(2000);
                    printDots(5);
                }
            }
            case GOAL_DENIED -> {
                if (!event.getVar()) {
                    System.out.printf("[GLT] %s Investigating validity%n", event.getAffectedTeam().getName());
                    Thread.sleep(2000);
                    printDots(5);
                    System.out.printf("%s shot is not over the line!%n", event.getAffectedTeam().getName());
                }
                else {
                    Random randomInvalidity = new Random();
                    String[] foulTexts = {"is offside", "committed a foul", "handball", "not a goal"};
                    System.out.printf("%s %s! Goal denied!%n", event.getAffectedTeam().getName(), foulTexts[randomInvalidity.nextInt(foulTexts.length)]);
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
                System.out.println("An object fell onto the field!");
                Thread.sleep(1500);
                System.out.println("The staff is cleaning up the pitch");
                printDots(3);
            }
            case INVADER -> {
                System.out.println("A fan ran onto the field!");
                Thread.sleep(1500);
                System.out.println("The security guards are being deployed");
                printDots(3);
            }
            default -> throw new IncorrectEventTypeException("Incorrect event type!");
        }
        Thread.sleep(1000);
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

    public static void printCoin(Team leftTeam, Team rightTeam, boolean coinValue) {
        System.out.printf("Coin flip: %s - HEADS, %s - TAILS%nThe coin landed on %s%n", leftTeam.getName(), rightTeam.getName(), coinValue ? "HEADS" : "TAILS");
    }

    public static void printPenaltyShootout(Team currentTeam, int number, boolean shot) throws InterruptedException {
        System.out.printf("%s No.%s shot:%n", currentTeam.getName(), number);
        Thread.sleep(500);
        printShot(currentTeam, shot);
    }

}
