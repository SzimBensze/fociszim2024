package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;

import java.util.Scanner;

public class UserInput {

    Scanner sc = new Scanner(System.in);

    public String inputName(Team currentTeam) {
        if (currentTeam.getClass().equals(Home.class)) System.out.printf("Type name for home team: ");
        else if (currentTeam.getClass().equals(Visitor.class)) System.out.printf("Type name for visitor team: ");
        return sc.nextLine();
    }

    public Integer inputPoints(String teamName, String pointType) {
        Integer points = 0;
        do {
            switch (pointType) {
                case "atk":
                    System.out.printf("%s attack points (1-99): ", teamName);
                    break;
                case "mid":
                    System.out.printf("%s midfield points (1-99): ", teamName);
                    break;
                case "def":
                    System.out.printf("%s defense points (1-99): ", teamName);
                    break;
                default:
                    System.out.printf("%s points (1-99): ");
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

}
