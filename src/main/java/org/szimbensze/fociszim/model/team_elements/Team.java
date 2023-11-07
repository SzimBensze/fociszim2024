package org.szimbensze.fociszim.model.team_elements;

/**
 * Football team class that includes all necessary data to play a game.
 */
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
    Float chanceDecreaseNumber;
    Float shotChanceMultiplier;
    Integer shots;
    Integer goals;

    public Team() {
        shots = 0;
        goals = 0;
    }

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

    public Float getChanceDecreaseNumber() {
        return chanceDecreaseNumber;
    }

    public void setChanceDecreaseNumber(Float chanceDecreaseNumber) {
        this.chanceDecreaseNumber = chanceDecreaseNumber;
    }

    public Float getShotChanceMultiplier() {
        return shotChanceMultiplier;
    }

    public void setShotChanceMultiplier(Float shotChanceMultiplier) {
        this.shotChanceMultiplier = shotChanceMultiplier;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    /**
     * Gets a team's accuracy by dividing its goals by the number of shots taken.
     * @return Percentage of a team's accuracy as a float value.
     */
    public Float getAccuracy() {
        if (shots.equals(0)) return 0F;
        else return goals.floatValue() / shots.floatValue() * 100F;
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
                ", chanceDecreaseNumber=" + chanceDecreaseNumber +
                ", shotChanceMultiplier" + shotChanceMultiplier +
                ", shots=" + shots +
                ", goals=" + goals +
                '}';
    }

}
