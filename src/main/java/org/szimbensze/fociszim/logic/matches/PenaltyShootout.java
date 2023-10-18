package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PenaltyShootout extends Match {

    private final Map<Team, String> penalties;

    public PenaltyShootout(Match matchBefore) {
        super(matchBefore.getTeamOne(), matchBefore.getTeamTwo());
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
        cyclePenalty(teamsInOrder, 5);

        TextPrinter.printGoalStats(teamOne, teamTwo);
        doHalftime();
        if (getWinner() == null) cyclePenalty(teamsInOrder, -1);
    }

    @Override
    protected void doHalftime() throws InterruptedException {
        TextPrinter.printPenaltyResults(teamOne, teamTwo, penalties.get(teamOne), penalties.get(teamTwo));
        Thread.sleep(2000);
    }

    private void cyclePenalty(List<Team> teamOrder, int penaltyAmount) throws InterruptedException {
        int penNum = 1;
        while (getWinner() == null || penNum <= penaltyAmount) {
            for (Team penaltyTaker : teamOrder) {
                boolean shot = shootPenalty(penaltyTaker, 900F);
                penalties.replace(penaltyTaker, penalties.get(penaltyTaker) + (shot ? "✓" : "✗"));
                TextPrinter.printPenaltyShootout(penaltyTaker, penNum, shot);
                Thread.sleep(500);
            }
        penNum++;
        if (penNum > penaltyAmount && penaltyAmount != -1) break;
        Thread.sleep(1000);
        }
    }

}
