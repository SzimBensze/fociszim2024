package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.FootballEvent;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Visitor;

import java.util.HashMap;

public abstract class Match {

    Home home;
    Visitor visitor;
    HashMap<Integer, FootballEvent> events;
    Integer firstMinute;
    Integer lastMinute;
    Integer currentMinute;

}
