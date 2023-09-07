package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Team;

public class ChanceCalculator {

    Float defaultChance;

    public ChanceCalculator(Float defaultChance) {
        this.defaultChance = defaultChance;
    }

    public Float CalcBaseChance(Team selected, Team opponent) {
        return defaultChance + (selected.getAtk() * selected.getLuck() - opponent.getDef() +
                selected.getMid() - opponent.getMid() +
                selected.getDef() - opponent.getAtk() * opponent.getLuck());
    }

    public Float CalcMinuteChance(Team selected, Team opponent) {
        Float chanceByBase = selected.getBaseChance() + (selected.getAtk() * selected.getLuck() - opponent.getDef() +
                selected.getMid() - opponent.getMid() +
                selected.getDef() - opponent.getAtk() * opponent.getLuck()) / 3;
        Float resultChance = chanceByBase / 10000;
        if (selected.getBaseChance() - opponent.getBaseChance() > 150) resultChance += 0.015F;
        return resultChance;
    }
}