package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.EventType;
import org.szimbensze.fociszim.model.FootballEvent;
import org.szimbensze.fociszim.model.Team;

import java.util.*;

public class EventRandomizer {

    Random random = new Random();

    public Map<Integer, FootballEvent> createEvents(Integer minMinute, Integer maxMinute, Integer amount, ArrayList<Team> teams) {
        Map<Integer, FootballEvent> randomEvents = new HashMap<>();
        for (int i = 0; i < random.nextInt(amount); i++) {
            EventType chosenEvent = EventType.values()[random.nextInt(EventType.values().length)];
            if (chosenEvent.affectBothTeams)
                randomEvents.put(random.nextInt(minMinute, maxMinute),
                    new FootballEvent(teams, chosenEvent));
            else randomEvents.put(random.nextInt(minMinute, maxMinute),
                new FootballEvent(
                        new ArrayList<>(
                                Collections.singletonList(
                                        teams.get(random.nextInt(teams.size())))), chosenEvent));
        }
        return randomEvents;
    }

}
