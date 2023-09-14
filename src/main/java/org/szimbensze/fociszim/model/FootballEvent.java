package org.szimbensze.fociszim.model;

public abstract class FootballEvent {

    protected EventType type;

    public FootballEvent(EventType type) {
        this.type = type;
    }

}
