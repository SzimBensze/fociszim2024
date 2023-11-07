package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.logic.ElementRandomizer;
import org.szimbensze.fociszim.logic.EventRandomizer;
import org.szimbensze.fociszim.model.events.*;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

import java.util.*;

public abstract class Match {

    /**
     * A team object visually represented on the left side.
     */
    Team teamOne;
    /**
     * A team object visually represented on the right side.
     */
    Team teamTwo;
    /**
     * First minute of the game.
     */
    Integer firstMinute;
    /**
     * Last minute of the game. Cycle plays through this minute as well.
     */
    Integer lastMinute;
    /**
     * The minute when doHalftime() method runs.
     */
    Integer halfTime;
    /**
     * Current minute which also serves as an index while cycling through.
     */
    Integer currentMinute;
    ElementRandomizer<Integer> firstHalfStoppageMinutes = new ElementRandomizer<>();
    ElementRandomizer<Integer> secHalfStoppageMinutes = new ElementRandomizer<>();
    Random random = new Random();
    ElementRandomizer<Integer> maxEventAmount = new ElementRandomizer<>();
    /**
     * Collection of events that will play through the match. The key is the minute when it occurs and the value is the event itself.
     */
    Map<Integer, FootballEvent> events;
    /**
     * Bool of stats display. When true the RNG display methods will run.
     */
    boolean isStatDisplay;

    public Match(Team team1, Team team2, boolean isStat) {
        this.teamOne = team1;
        this.teamTwo = team2;
        this.isStatDisplay = isStat;
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

    /**
     * The first method that runs when creating a new match, it initiates the process and fills up the necessary fields. (Recommended to include base method when overriding.)
     * @throws InterruptedException Throws at keyboard-interrupt.
     */
    public void initiateMatch() throws InterruptedException {
        events = EventRandomizer.createEvents(firstMinute, lastMinute, maxEventAmount.next(), new ArrayList<>(Arrays.asList(teamOne, teamTwo)));
        currentMinute = firstMinute;
        try {
            playMatch();
        } catch (InterruptedException e) {
            TextPrinter.printInterrupted();
        }
    }

    /**
     * The core method which starts the match cycle and runs each necessary method for the gameplay.
     * @throws InterruptedException Throws at keyboard-interrupt.
     */
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

    /**
     * Simulation of a single minute where each RNG and chance is compared.
     * @param isStoppageTime If present, no events will play.
     * @throws InterruptedException Throws at keyboard-interrupt.
     */
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

    /**
     * Cycle of added minutes which depend on RNG and the amount of events present in the match.
     * @param addedMinutes The amount of stoppage time.
     * @throws InterruptedException Throws at keyboard-interrupt.
     */
    private void playStoppageTime(Integer addedMinutes) throws InterruptedException {
        if (events.size() >= 2) addedMinutes += 1;
        if (events.size() >= 3) addedMinutes += 2;
        for (int i = 1; i <= addedMinutes; i++) {
            TextPrinter.printMinute(currentMinute, i);
            playMinute(true);
            Thread.sleep(500);
        }
    }

    /**
     * Checks if the RNG is smaller than the team's overall chance.
     * @param currentTeam Selected team.
     * @param chanceMultiplier The team's chance multiplier (constant).
     * @return If true, a shot will be taken.
     */
    private boolean checkShot(Team currentTeam, Float chanceMultiplier) {
        return random.nextFloat() < currentTeam.getMinuteChance() * chanceMultiplier;
    }

    /**
     * Method that makes the selected team shoot for a goal. Goal depends on minute chance and opponent's stats as well as RNG.
     * @param currentTeam Selected team.
     * @param opponentTeam Opponent team.
     * @param chanceMultiplier The team's chance multiplier (constant).
     * @return If true, the selected team scores a goal.
     */
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

    /**
     * Method that makes the selected team shoot a penalty. Penalty uses the team's base chance value for RNG.
     * @param currentTeam Selected team.
     * @param hitMaxValue Maximum value of RNG. The higher the number the harder to score a goal.
     * @return If true, the selected team scores a penalty.
     */
    protected boolean shootPenalty(Team currentTeam, Float hitMaxValue) {
        currentTeam.setShots(currentTeam.getShots() + 1);
        float randomValue = random.nextFloat(hitMaxValue);
        boolean successfulShot = randomValue < currentTeam.getBaseChance();
        if (successfulShot) currentTeam.setGoals(currentTeam.getGoals() + 1);
        if (isStatDisplay) TextPrinter.printStatNumbers(currentTeam, randomValue, true);
        return successfulShot;
    }

    /**
     * Method that runs when the current minute reaches halftime.
     * @throws InterruptedException Throws at keyboard-interrupt.
     */
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
            if (isStatDisplay) TextPrinter.printStatNumbers(((SingleTeamEvent) event).getAffectedTeam(), null, false);
        } else if (event instanceof TwoTeamEvent) {
            TextPrinter.printDuoEvent((TwoTeamEvent) event);
            for (Team affected : ((TwoTeamEvent) event).getAffectedTeams()){
                affected.setMinuteChance(affected.getMinuteChance() + event.getType().chanceModifier);
                if (isStatDisplay) TextPrinter.printStatNumbers(affected, null, false);
            }
        }
    }

    /**
     * Checks which team has more goals.
     * @return Returns the team with more goals. If none, null will be returned.
     */
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
