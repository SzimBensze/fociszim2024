package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.events.EventType;
import org.szimbensze.fociszim.model.events.FootballEvent;
import org.szimbensze.fociszim.model.events.SingleTeamEvent;
import org.szimbensze.fociszim.model.events.TwoTeamEvent;
import org.szimbensze.fociszim.model.team_elements.Team;

import java.util.*;

public class EventRandomizer {

    private static final Random random = new Random();
    private static final ElementRandomizer<EventType> eventsSelection = new ElementRandomizer<>();

    public static Map<Integer, FootballEvent> createEvents(Integer minMinute, Integer maxMinute, Integer amount, ArrayList<Team> teams) {
        createEventsList();
        Map<Integer, FootballEvent> randomEvents = new HashMap<>();
        for (int i = 0; i < random.nextInt(amount); i++) {
            EventType chosenEvent = eventsSelection.next();
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

    private static void createEventsList() {
        eventsSelection.add(16, EventType.PENALTY);
        eventsSelection.add(22, EventType.YELLOW_CARD);
        eventsSelection.add(12, EventType.RED_CARD);
        eventsSelection.add(5, EventType.VAR_GOAL);
        eventsSelection.add(10, EventType.GOAL_DENIED);
        eventsSelection.add(6, EventType.INJURY);
        eventsSelection.add(8, EventType.OBSTRUCTION);
        eventsSelection.add(8, EventType.INVADER);
        eventsSelection.add(13, EventType.NOT_FOUL);
    }

}
