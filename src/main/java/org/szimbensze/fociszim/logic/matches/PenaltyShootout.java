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
    public void initiateMatch(Float defaultChanceMultiplier) throws InterruptedException {
        TextPrinter.printDivider();
        chanceMultiplier = defaultChanceMultiplier;
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

        List<Team> teamOrder = headsOrTails ? Arrays.asList(teamOne, teamTwo) : Arrays.asList(teamTwo, teamOne);
        for (int i = 1; i <= 5; i++) {
            for (Team penaltyTaker : teamOrder) {
                boolean shot = shootPenalty(penaltyTaker, 900F);
                penalties.replace(penaltyTaker, penalties.get(penaltyTaker) + (shot ? "✓" : "✗"));
                TextPrinter.printPenaltyShootout(penaltyTaker, i, shot);
                Thread.sleep(1000);
            }
        }
        TextPrinter.printGoalStats(teamOne, teamTwo);
        TextPrinter.printPenaltyResults(teamOne, teamTwo, penalties.get(teamOne), penalties.get(teamTwo));
        doHalftime();
    }

    @Override
    protected void doHalftime() throws InterruptedException {
        if (getWinner() == null) {
            System.out.println("WIP");
            //TODO penalties until winner announced
        }
    }
}
