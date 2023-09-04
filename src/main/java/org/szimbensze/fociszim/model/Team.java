package org.szimbensze.fociszim.model;

public abstract class Team {

    String name;
    Integer atk;
    Integer mid;
    Integer def;
    Formation formation;
    Float luck;
    Float baseChance;
    Float minuteChance;
    Float minuteChanceModifier;
    Float minuteDecreaseMultiplier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Float getLuck() {
        return luck;
    }

    public void setLuck(Float luck) {
        this.luck = luck;
    }

    public Float getBaseChance() {
        return baseChance;
    }

    public void setBaseChance(Float baseChance) {
        this.baseChance = baseChance;
    }

    public Float getMinuteChance() {
        return minuteChance;
    }

    public void setMinuteChance(Float minuteChance) {
        this.minuteChance = minuteChance;
    }

    public Float getMinuteChanceModifier() {
        return minuteChanceModifier;
    }

    public void setMinuteChanceModifier(Float minuteChanceModifier) {
        this.minuteChanceModifier = minuteChanceModifier;
    }

    public Float getMinuteDecreaseMultiplier() {
        return minuteDecreaseMultiplier;
    }

    public void setMinuteDecreaseMultiplier(Float minuteDecreaseMultiplier) {
        this.minuteDecreaseMultiplier = minuteDecreaseMultiplier;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", atk=" + atk +
                ", mid=" + mid +
                ", def=" + def +
                ", formation=" + formation +
                ", luck=" + luck +
                ", baseChance=" + baseChance +
                ", minuteChance=" + minuteChance +
                ", minuteChanceModifier=" + minuteChanceModifier +
                ", minuteDecreaseMultiplier=" + minuteDecreaseMultiplier +
                '}';
    }
}
