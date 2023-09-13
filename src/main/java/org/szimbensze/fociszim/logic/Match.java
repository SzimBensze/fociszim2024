package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.FootballEvent;
import org.szimbensze.fociszim.model.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public abstract class Match {

    Team teamOne;
    Team teamTwo;
    Integer firstMinute;
    Integer lastMinute;
    Integer currentMinute;
    EventRandomizer eventRandom = new EventRandomizer();
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

    public Integer getCurrentMinute() {
        return currentMinute;
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

    public void initiateMatch() {
        events = eventRandom.createEvents(firstMinute, lastMinute, maxEventAmount, new ArrayList<>(Arrays.asList(teamOne, teamTwo)));
        currentMinute = firstMinute;
    }

    @Override
    public String toString() {
        return "Match{" +
                "teamOne=" + teamOne +
                ", teamTwo=" + teamTwo +
                ", firstMinute=" + firstMinute +
                ", lastMinute=" + lastMinute +
                ", currentMinute=" + currentMinute +
                ", maxEventAmount=" + maxEventAmount +
                ", events=" + events +
                '}';
    }

}
