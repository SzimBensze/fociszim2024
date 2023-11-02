package org.szimbensze.fociszim;

import org.szimbensze.fociszim.logic.matches.*;
import org.szimbensze.fociszim.visual.*;

/**
 * The main class.
 */
public class Main {

    /**
     * The main method which executes each launch.
     * @param args No arguments necessary.
     * @throws InterruptedException Throws when a simulation is interrupted by a keyboard interrupt.
     */
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Szim's Football Simulator 2024");
        System.out.println("Please note that the program is still work-in-progress! You might experience bugs and unintended gameplay.");
        TextPrinter.printDivider();

        LocationType location = UserInput.inputYesNo("Invitational match (is there home side)? (y/n)") ? LocationType.HOME_STADIUM : LocationType.OUTSIDE;
        boolean isStat = UserInput.inputYesNo("Display stats? (y/n)");
        TextPrinter.printDivider();

        Match90 baseMatch = MatchBuilder.createBaseMatch(location, isStat);
        baseMatch.initiateMatch();
        if (baseMatch.getWinner() == null) {
            if (UserInput.inputYesNo("Continue with overtime? (y/n)")) {
                Match120 extraTime = MatchBuilder.createExtraMatch(baseMatch);
                extraTime.initiateMatch();
                if (extraTime.getWinner() == null) {
                    if (UserInput.inputYesNo("Continue with penalties? (y/n)")) {
                        PenaltyShootout penalties = MatchBuilder.createPenaltyShootout(extraTime);
                        penalties.initiateMatch();
                    }
                }
            }
        }
        TextPrinter.printWinner(baseMatch.getWinner());

        TextPrinter.printDivider();
        //Thread.sleep(Long.MAX_VALUE);
    }

}
