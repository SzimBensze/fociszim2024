package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.logic.ElementRandomizer;
import org.szimbensze.fociszim.logic.EventRandomizer;
import org.szimbensze.fociszim.model.events.*;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

import java.util.*;

public abstract class Match {

    Team teamOne;
    Team teamTwo;
    Integer firstMinute;
    Integer lastMinute;
    Integer halfTime;
    Integer currentMinute;
    ElementRandomizer<Integer> firstHalfStoppageMinutes = new ElementRandomizer<>();
    ElementRandomizer<Integer> secHalfStoppageMinutes = new ElementRandomizer<>();
    Random random = new Random();
    Integer maxEventAmount;
    Map<Integer, FootballEvent> events;
    boolean isStatDisplay;

    public Match(Team team1, Team team2, boolean isStat) {
        this.teamOne = team1;
        this.teamTwo = team2;
        this.isStatDisplay = isStat;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setFirstMinute(Integer firstMinute) {
        this.firstMinute = firstMinute;
    }

    public void setLastMinute(Integer lastMinute) {
        this.lastMinute = lastMinute;
    }

    public void setHalfTime(Integer halfTime) {
        this.halfTime = halfTime;
    }

    public void setSecHalfStoppageMinutes(ElementRandomizer<Integer> secHalfStoppageMinutes) {
        this.secHalfStoppageMinutes = secHalfStoppageMinutes;
    }

    public void setMaxEventAmount(Integer maxEventAmount) {
        this.maxEventAmount = maxEventAmount;
    }

    public Map<Integer, FootballEvent> getEvents() {
        return events;
    }

    public void initiateMatch() throws InterruptedException {
        events = EventRandomizer.createEvents(firstMinute, lastMinute, maxEventAmount, new ArrayList<>(Arrays.asList(teamOne, teamTwo)));
        currentMinute = firstMinute;
        try {
            playMatch();
        } catch (InterruptedException e) {
            TextPrinter.printInterrupted();
        }
    }

    protected void playMatch() throws InterruptedException {
        while (currentMinute <= lastMinute) {
            TextPrinter.printMinute(currentMinute, 0);
            playMinute(false);
            Thread.sleep(500);
            if (currentMinute.equals(halfTime)) {
                playStoppageTime(firstHalfStoppageMinutes.next());
                doHalftime();
            } else if (currentMinute.equals(lastMinute)) playStoppageTime(secHalfStoppageMinutes.next());
            currentMinute++;
        }
        TextPrinter.printGoalStats(teamOne, teamTwo);
    }

    private void playMinute(boolean isStoppageTime) throws InterruptedException {
        if (checkShot(teamOne, teamOne.getShotChanceMultiplier())) {
            TextPrinter.printShot(teamOne, shoot(teamOne, teamTwo, teamOne.getShotChanceMultiplier()));
        }
        if (checkShot(teamTwo, teamTwo.getShotChanceMultiplier())) {
            TextPrinter.printShot(teamTwo, shoot(teamTwo, teamOne, teamTwo.getShotChanceMultiplier()));
        }
        if (!isStoppageTime) checkEvent();

        teamOne.setMinuteChance(teamOne.getMinuteChance() + teamOne.getMinuteChanceModifier() - (teamTwo.getAtk() + teamTwo.getMid() + teamTwo.getDef()) / 100000F);
        teamTwo.setMinuteChance(teamTwo.getMinuteChance() + teamTwo.getMinuteChanceModifier() - (teamOne.getAtk() + teamOne.getMid() + teamOne.getDef()) / 100000F);
        if (teamOne.getMinuteChance() < 0) teamOne.setMinuteChance(0.0001F);
        if (teamTwo.getMinuteChance() < 0) teamTwo.setMinuteChance(0.0001F);
    }

    private void playStoppageTime(Integer addedMinutes) throws InterruptedException {
        if (events.size() >= 2) addedMinutes += 1;
        if (events.size() >= 3) addedMinutes += 2;
        for (int i = 1; i <= addedMinutes; i++) {
            TextPrinter.printMinute(currentMinute, i);
            playMinute(true);
            Thread.sleep(500);
        }
    }

    private boolean checkShot(Team currentTeam, Float chanceMultiplier) {
        return random.nextFloat() < currentTeam.getMinuteChance() * chanceMultiplier;
    }

    private boolean shoot(Team currentTeam, Team opponentTeam, Float chanceMultiplier) {
        currentTeam.setShots(currentTeam.getShots() + 1);
        float randomValue = random.nextFloat();
        boolean successfulShot = randomValue < currentTeam.getMinuteChance() *
                (chanceMultiplier +
                        currentTeam.getAtk().floatValue() / 100F -
                        opponentTeam.getDef().floatValue() / 100F);
        if (successfulShot) {
            currentTeam.setGoals(currentTeam.getGoals() + 1);
            currentTeam.setMinuteChance(currentTeam.getMinuteChance() - currentTeam.getChanceDecreaseNumber());
        }
        if (isStatDisplay) TextPrinter.printStatNumbers(currentTeam, randomValue, false);
        return successfulShot;
    }

    protected boolean shootPenalty(Team currentTeam, Float hitMaxValue) {
        currentTeam.setShots(currentTeam.getShots() + 1);
        float randomValue = random.nextFloat(hitMaxValue);
        boolean successfulShot = randomValue < currentTeam.getBaseChance();
        if (successfulShot) currentTeam.setGoals(currentTeam.getGoals() + 1);
        if (isStatDisplay) TextPrinter.printStatNumbers(currentTeam, randomValue, true);
        return successfulShot;
    }

    protected abstract void doHalftime() throws InterruptedException;

    private void checkEvent() throws InterruptedException {
        if (events.containsKey(currentMinute)) {
            try {
                playEvent(events.get(currentMinute));
            } catch (IncorrectEventTypeException e) {
                //TODO event incorrect type message
            }
        }
    }

    private void playEvent(FootballEvent event) throws InterruptedException, IncorrectEventTypeException {
        if (event instanceof SingleTeamEvent) {
            TextPrinter.printSingleEvent((SingleTeamEvent) event);
            switch (event.getType()) {
                case PENALTY :
                    Team penaltyTaker = ((SingleTeamEvent) event).getAffectedTeam();
                    TextPrinter.printShot(penaltyTaker, shootPenalty(penaltyTaker, 1000F));
                    Thread.sleep(500);
                    break;
                case VAR_GOAL :
                    Team givenTeam = ((SingleTeamEvent) event).getAffectedTeam();
                    givenTeam.setShots(givenTeam.getShots() + 1);
                    givenTeam.setGoals(givenTeam.getGoals() + 1);
                    TextPrinter.printShot(givenTeam, true);
                default :
                    if (((SingleTeamEvent) event).getVar())
                        ((SingleTeamEvent) event).getAffectedTeam().setMinuteChance(
                            ((SingleTeamEvent) event).getAffectedTeam().getMinuteChance()
                                    + event.getType().chanceModifier);
                    else ((SingleTeamEvent) event).getAffectedTeam().setMinuteChance(
                            ((SingleTeamEvent) event).getAffectedTeam().getMinuteChance()
                                    + event.getType().chanceModifier * 2);
            }
        } else if (event instanceof TwoTeamEvent) {
            TextPrinter.printDuoEvent((TwoTeamEvent) event);
            for (Team affected : ((TwoTeamEvent) event).getAffectedTeams())
                affected.setMinuteChance(affected.getMinuteChance() + event.getType().chanceModifier);
        }
    }

    public Team getWinner() {
        if (teamOne.getGoals() > teamTwo.getGoals()) return teamOne;
        else if (teamTwo.getGoals() > teamOne.getGoals()) return teamTwo;
        else return null;
    }

    @Override
    public String toString() {
        return "Match{" +
                "teamOne=" + teamOne +
                ", teamTwo=" + teamTwo +
                ", firstMinute=" + firstMinute +
                ", lastMinute=" + lastMinute +
                ", halfTime=" + halfTime +
                ", currentMinute=" + currentMinute +
                ", maxEventAmount=" + maxEventAmount +
                ", events=" + events +
                '}';
    }

}
