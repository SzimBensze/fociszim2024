package org.szimbensze.fociszim.model;

public abstract class Team {

    String name;
    Integer atk;
    Integer mid;
    Integer def;
    Formation formation;
    Float baseChance;
    Float minuteChance;

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
}
