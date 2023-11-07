package org.szimbensze.fociszim.model.events;

/**
 * An event object that can and must have a valid event type field in order to work. The type determines the inherited class.
 */
public abstract class FootballEvent {

    protected EventType type;

    public FootballEvent(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

}
