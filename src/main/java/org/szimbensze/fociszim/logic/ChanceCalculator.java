package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Team;

import java.util.Random;

public class ChanceCalculator {

    Float baseChance;

    public ChanceCalculator(Float baseChance) {
        this.baseChance = baseChance;
    }

    public Float CalcBaseChance(Team selected, Team opponent) {
        return baseChance + (selected.getAtk() * selected.getLuck() - opponent.getDef() +
                selected.getMid() - opponent.getMid() +
                selected.getDef() - opponent.getAtk() * opponent.getLuck());
    }

    public Float CalcMinuteChance(Team selected, Team opponent) {
        return null;
    }
}
