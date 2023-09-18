package org.szimbensze.fociszim.logic.matches;

import org.szimbensze.fociszim.logic.EventRandomizer;
import org.szimbensze.fociszim.model.events.FootballEvent;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.TextPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public abstract class Match {

    Team teamOne;
    Team teamTwo;
    Integer firstMinute;
    Integer lastMinute;
    Integer halfTime;
    Integer currentMinute;
    Float chanceMultiplier;
    EventRandomizer eventRandom = new EventRandomizer();
    Random random = new Random();
    Integer maxEventAmount;
    Map<Integer, FootballEvent> events;

    public Match(Team team1, Team team2) {
        this.teamOne = team1;
        this.teamTwo = team2;
    }

    public Integer getFirstMinute() {
        return firstMinute;
    }

    public void setFirstMinute(Integer firstMinute) {
        this.firstMinute = firstMinute;
    }

    public Integer getLastMinute() {
        return lastMinute;
    }

    public void setLastMinute(Integer lastMinute) {
        this.lastMinute = lastMinute;
    }

    public Integer getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(Integer halfTime) {
        this.halfTime = halfTime;
    }

    public Integer getCurrentMinute() {
        return currentMinute;
    }

    public Float getChanceMultiplier() {
        return chanceMultiplier;
    }

    public void setChanceMultiplier(Float chanceMultiplier) {
        this.chanceMultiplier = chanceMultiplier;
    }

    public Integer getMaxEventAmount() {
        return maxEventAmount;
    }

    public void setMaxEventAmount(Integer maxEventAmount) {
        this.maxEventAmount = maxEventAmount;
    }

    public Map<Integer, FootballEvent> getEvents() {
        return events;
    }

    public void setEvents(Map<Integer, FootballEvent> events) {
        this.events = events;
    }

    public void initiateMatch(Float defaultChanceMultiplier) throws InterruptedException {
        events = eventRandom.createEvents(firstMinute, lastMinute, maxEventAmount, new ArrayList<>(Arrays.asList(teamOne, teamTwo)));
        currentMinute = firstMinute;
        chanceMultiplier = defaultChanceMultiplier;
        try {
            playMatch();
        } catch (InterruptedException e) {
            TextPrinter.printInterrupted();
        }
    }

    private void playMatch() throws InterruptedException {
        while (currentMinute < lastMinute) {
            TextPrinter.printRound(currentMinute);
            if (checkShot(teamOne, chanceMultiplier)) {
                TextPrinter.printShot(teamOne, shoot(teamOne, teamTwo, chanceMultiplier));
            }
            if (checkShot(teamTwo, chanceMultiplier)) {
                TextPrinter.printShot(teamTwo, shoot(teamTwo, teamOne, chanceMultiplier));
            }
            if (currentMinute.equals(halfTime)) doHalftime();
            currentMinute++;
            Thread.sleep(500);
        }
    }

    private boolean checkShot(Team currentTeam, Float chanceMultiplier) {
        return random.nextFloat() < currentTeam.getMinuteChance() * chanceMultiplier;
    }

    private boolean shoot(Team currentTeam, Team opponentTeam, Float chanceMultiplier) {
        currentTeam.setShots(currentTeam.getShots() + 1);
        boolean successfulShot = random.nextFloat() < currentTeam.getMinuteChance() *
                (chanceMultiplier +
                        currentTeam.getAtk().floatValue() / 100F -
                        opponentTeam.getDef().floatValue() / 100F);
        if (successfulShot) currentTeam.setGoals(currentTeam.getGoals() + 1);
        return successfulShot;
    }

    private void doHalftime() throws InterruptedException {
        TextPrinter.printHalftime();
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
