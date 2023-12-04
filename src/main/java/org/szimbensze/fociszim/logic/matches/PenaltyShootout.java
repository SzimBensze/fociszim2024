package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PenaltyShootout extends Match {

    private final Map<Team, String> penalties;

    public PenaltyShootout(Match prevMatch) {
        super(prevMatch.teamOne, prevMatch.teamTwo, prevMatch.isStatDisplay);
        penalties = new HashMap<>();
        penalties.put(teamOne, "");
        penalties.put(teamTwo, "");
    }

    @Override
    public void initiateMatch() throws InterruptedException {
        TextPrinter.printDivider();
        try {
            playMatch();
        } catch (InterruptedException e) {
            TextPrinter.printInterrupted();
        }
    }

    @Override
    protected void playMatch() throws InterruptedException {
        boolean headsOrTails = random.nextBoolean();
        TextPrinter.printCoin(teamOne, teamTwo, headsOrTails);
        Thread.sleep(2000);

        List<Team> teamsInOrder = headsOrTails ? Arrays.asList(teamOne, teamTwo) : Arrays.asList(teamTwo, teamOne);
        cyclePenalty(teamsInOrder, 5, 1);

        doHalftime();
        if (getWinner() == null) cyclePenalty(teamsInOrder, -1, 6);
    }

    @Override
    protected void doHalftime() throws InterruptedException {
        Thread.sleep(2000);
    }

    /**
     * Cycles through the given amount of penalties in the given order.
     * @param teamOrder The order in which teams are taking the penalties.
     * @param penaltyAmount The amount of penalties teams take. If set to -1 teams shoot until one of them fails.
     * @param currentPenaltyNumber The number of the first penalty. Must be 1 in order to shoot all penalties if penaltyAmount is not -1.
     * @throws InterruptedException Throws at keyboard-interrupt.
     */
    private void cyclePenalty(List<Team> teamOrder, int penaltyAmount, int currentPenaltyNumber) throws InterruptedException {
        while (getWinner() == null || currentPenaltyNumber <= penaltyAmount) {
            for (Team penaltyTaker : teamOrder) {
                boolean shot = shootPenalty(penaltyTaker, 900F);
                penalties.replace(penaltyTaker, penalties.get(penaltyTaker) + (shot ? "✓" : "✗"));
                TextPrinter.printPenaltyShootout(penaltyTaker, currentPenaltyNumber, shot);
                Thread.sleep(500);
            }
        currentPenaltyNumber++;
        if (currentPenaltyNumber > penaltyAmount && penaltyAmount != -1) break;
        Thread.sleep(1000);
        }
        TextPrinter.printPenaltyResults(teamOne, teamTwo, penalties.get(teamOne), penalties.get(teamTwo));
    }

}
