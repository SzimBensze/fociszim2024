package org.szimbensze.fociszim;

import org.szimbensze.fociszim.logic.matches.*;
import org.szimbensze.fociszim.visual.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Szim's Football Simulator 2024");
        System.out.println("The program is currently work in progress! There's not much to look at yet, please check back later.");
        TextPrinter.printDivider();

        LocationType location = UserInput.inputYesNo("Invitational match (is there home side)? (y/n)") ? LocationType.HOME_STADIUM : LocationType.OUTSIDE;
        boolean isStat = UserInput.inputYesNo("Display stats? (y/n)");
        TextPrinter.printDivider();

        Match90 baseMatch = MatchBuilder.createBaseMatch(location, isStat);
        baseMatch.initiateMatch(1F);
        if (baseMatch.getWinner() == null) {
            if (UserInput.inputYesNo("Continue with overtime? (y/n)")) {
                Match120 extraTime = MatchBuilder.createExtraMatch(baseMatch);
                extraTime.initiateMatch(1.05F);
                if (extraTime.getWinner() == null) {
                    if (UserInput.inputYesNo("Continue with penalties? (y/n)")) {
                        PenaltyShootout penalties = MatchBuilder.createPenaltyShootout(extraTime);
                        penalties.initiateMatch(1F);
                    }
                }
            }
        }
        TextPrinter.printWinner(baseMatch.getWinner());

        TextPrinter.printDivider();
        //Thread.sleep(Long.MAX_VALUE);
    }

}
