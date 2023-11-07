package org.szimbensze.fociszim.visual;

import org.szimbensze.fociszim.model.team_elements.Home;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.model.team_elements.Visitor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInput {

    static Scanner sc = new Scanner(System.in);

    public String inputName(Team currentTeam) {
        if (currentTeam.getClass().equals(Home.class)) System.out.print("Type name for home team: ");
        else if (currentTeam.getClass().equals(Visitor.class)) System.out.print("Type name for visitor team: ");
        return sc.nextLine();
    }

    /**
     * Gets a user input as base points between values of 1 and 99.
     * @param teamName Selected team's name.
     * @param pointType The base point type. "atk" = attack points, "mid" = midfield points, "def" = defense points
     * @return Returns the input value as an integer (reference type).
     */
    public Integer inputPoints(String teamName, String pointType) {
        int points = 0;
        do {
            switch (pointType) {
                case "atk" -> System.out.printf("%s attack points (1-99): ", teamName);
                case "mid" -> System.out.printf("%s midfield points (1-99): ", teamName);
                case "def" -> System.out.printf("%s defense points (1-99): ", teamName);
                default -> System.out.printf("%s points (1-99): ", teamName);
            }
            try {
                points = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException ex) {
                System.out.println("Not a number!");
            }
            if (points < 1 || points > 99) System.out.println("Incorrect value!");
        } while (points < 1 || points > 99);
        return points;
    }

    /**
     * Uses a regex to validate an input value as a formation code, such as "4-2-3-1".
     * @param teamName Selected team's name.
     * @return Returns a validated string that can be used to determine a Formation object.
     */
    public String inputFormation(String teamName) {
        System.out.printf("Formation for %s:%n", teamName);
        while (true) {
            System.out.print("Please use one of the following formats for formations:\n" +
                    "5-4-1 OR 5-2-2-1 OR 4-2-2-1-1: ");
            Pattern pattern = Pattern.compile("^[0-9]-[0-9]-[0-9](-[0-9])?(-[0-9])?$");
            String rawFormation = sc.nextLine();
            Matcher matcher = pattern.matcher(rawFormation);
            if (matcher.find()) return rawFormation;
        }
    }

    /**
     * Answers a yes/no question with user input.
     * @param question The question displayed on the console.
     * @return Returns true if the answer is positive ("y" or "yes") and false ("n" or "no") if negative.
     */
    public static boolean inputYesNo(String question) {
        System.out.println(question);
        while (true) {
            System.out.print("Please answer with yes/no (y/n): ");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) return true;
            else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) return false;
        }
    }

}
