package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PenaltyShootout extends Match {

    private String penaltiesTeamOne;
    private String penaltiesTeamTwo;

    public PenaltyShootout(Match matchBefore) {
        super(matchBefore.getTeamOne(), matchBefore.getTeamTwo());
        penaltiesTeamOne = "";
        penaltiesTeamTwo = "";
    }

    @Override
    public void initiateMatch(Float defaultChanceMultiplier) throws InterruptedException {
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
        System.out.println("-----");

        List<Team> teamOrder = headsOrTails ? Arrays.asList(teamOne, teamTwo) : Arrays.asList(teamTwo, teamOne);
        for (int i = 1; i <= 5; i++) {
            for (Team penaltyTaker : teamOrder) {
                TextPrinter.printPenaltyShootout(penaltyTaker, i, shootPenalty(penaltyTaker, 900F));
                Thread.sleep(1000);
            }
        }
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
