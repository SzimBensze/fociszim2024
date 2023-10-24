package org.szimbensze.fociszim.logic.matches;

/**
 * Determines the location which defines the types of the two teams for the match.
 */
public enum LocationType {
    /**
     * The location is in one of the teams' stadium which makes one home and one visitor team.
     */
    HOME_STADIUM,
    /**
     * The location differs from each teams' home side so there are two visitor teams.
     */
    OUTSIDE
}
