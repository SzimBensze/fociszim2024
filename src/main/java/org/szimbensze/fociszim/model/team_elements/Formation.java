package org.szimbensze.fociszim.model.team_elements;

/**
 * Football formation that adds extra points to a team. It is always chosen from the Formations collection class.
 * @param formationName The formation code standardised by football associations (e.g. 4-2-3-1).
 * @param addedAtk Added attack points. Can be negative.
 * @param addedMid Added midfield points. Can be negative.
 * @param addedDef Added defense points. Can be negative.
 */
public record Formation(String formationName, int addedAtk, int addedMid, int addedDef) { }
