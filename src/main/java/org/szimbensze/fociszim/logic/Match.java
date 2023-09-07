package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.FootballEvent;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Visitor;

import java.util.HashMap;

public abstract class Match {

    Home home;
    Visitor visitor;
    Integer firstMinute;
    Integer lastMinute;
    Integer currentMinute;
    ChanceCalculator chanceCalc;
    EventRandomizer eventRandom;
    HashMap<Integer, FootballEvent> events;

}