package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.events.EventType;
import org.szimbensze.fociszim.model.events.FootballEvent;
import org.szimbensze.fociszim.model.events.SingleTeamEvent;
import org.szimbensze.fociszim.model.events.TwoTeamEvent;
import org.szimbensze.fociszim.model.team_elements.Team;

import java.util.*;

public class EventRandomizer {

    Random random = new Random();

    public Map<Integer, FootballEvent> createEvents(Integer minMinute, Integer maxMinute, Integer amount, ArrayList<Team> teams) {
        Map<Integer, FootballEvent> randomEvents = new HashMap<>();
        for (int i = 0; i < random.nextInt(amount); i++) {
            EventType chosenEvent = EventType.values()[random.nextInt(EventType.values().length)];
            if (chosenEvent.affectBothTeams)
                randomEvents.put(random.nextInt(minMinute, maxMinute),
                        new TwoTeamEvent(chosenEvent, teams));
            else if (chosenEvent.canBeVar)
                randomEvents.put(random.nextInt(minMinute, maxMinute),
                        new SingleTeamEvent(chosenEvent, random.nextBoolean(), teams.get(random.nextInt(teams.size()))));
            else randomEvents.put(random.nextInt(minMinute, maxMinute),
                        new SingleTeamEvent(chosenEvent, false, teams.get(random.nextInt(teams.size()))));
        }
        return randomEvents;
    }

}
