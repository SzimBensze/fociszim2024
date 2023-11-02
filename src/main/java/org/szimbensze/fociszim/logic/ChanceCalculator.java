package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.team_elements.Team;

public class ChanceCalculator {

    Float defaultChance;

    public ChanceCalculator(Float defaultChance) {
        this.defaultChance = defaultChance;
    }

    /**
     * Uses the selected and opponent teams' base values and luck to create a float value of chance.
     * @param selected Selected team.
     * @param opponent Opponent team. It's necessary to be able to calculate the base chance.
     * @return Returns a float value which serves as a base value of chance for the team. It's used at kick-off and penalties.
     */
    public Float CalcBaseChance(Team selected, Team opponent) {
        return defaultChance + (selected.getAtk() * selected.getLuck() - opponent.getDef() +
                selected.getMid() - opponent.getMid() +
                selected.getDef() - opponent.getAtk() * opponent.getLuck());
    }

    /**
     * Creates a value between 0 and 1 which represents chance to shoot for goal for the team. It slightly modifies each minute depending on the team's power, base chance, luck and events.
     * @param selected Selected team.
     * @param opponent Opponent team.
     * @return Returns a float value which serves as chance for the team.
     */
    public Float CalcMinuteChance(Team selected, Team opponent) {
        float chanceByBase = selected.getBaseChance() + (selected.getAtk() * selected.getLuck() - opponent.getDef() +
                selected.getMid() - opponent.getMid() +
                selected.getDef() - opponent.getAtk() * opponent.getLuck()) / 3;
        float resultChance = chanceByBase / 10000;
        if (selected.getBaseChance() - opponent.getBaseChance() > 150) resultChance += 0.015F;
        return resultChance;
    }

}
