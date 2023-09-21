package org.szimbensze.fociszim.model.events;

public abstract class FootballEvent {

    protected EventType type;

    public FootballEvent(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

}
