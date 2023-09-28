package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.team_elements.Formations;
import org.szimbensze.fociszim.model.team_elements.Team;
import org.szimbensze.fociszim.visual.UserInput;

import java.util.Random;

public class TeamCreator {

    UserInput userInput = new UserInput();
    Random random = new Random();

    public void addStats(Team selectedTeam) {
        selectedTeam.setName(userInput.inputName(selectedTeam));
        addPoints(selectedTeam, userInput.inputPoints(selectedTeam.getName(), "atk"),
            userInput.inputPoints(selectedTeam.getName(), "mid"),
            userInput.inputPoints(selectedTeam.getName(), "def"));
        addLuck(selectedTeam, 1F, 0.5F);
        addFormation(selectedTeam);
    }

    private void addPoints(Team selectedTeam, Integer atk, Integer mid, Integer def) {
        selectedTeam.setAtk(atk);
        selectedTeam.setMid(mid);
        selectedTeam.setDef(def);
    }

    private void addLuck(Team selectedTeam, Float baseLuck, Float luckMultiplier) {
        selectedTeam.setLuck(baseLuck + random.nextFloat() * luckMultiplier);
    }

    private void addFormation(Team selectedTeam) {
        String rawFormation = userInput.inputFormation(selectedTeam.getName());
        selectedTeam.setFormation(Formations.getFormation(rawFormation));
    }

    private void addFormationPoints(Team selectedTeam) {
        selectedTeam.setAtk(selectedTeam.getAtk() + selectedTeam.getFormation().addedAtk());
        selectedTeam.setMid(selectedTeam.getMid() + selectedTeam.getFormation().addedMid());
        selectedTeam.setDef(selectedTeam.getDef() + selectedTeam.getFormation().addedDef());
    }

    public void addBaseChance(Team selectedTeam, Float baseChance) {
        selectedTeam.setBaseChance(baseChance);
    }

    public void addMinuteChance(Team selectedTeam, Float minuteChance) {
        addFormationPoints(selectedTeam);
        addLuck(selectedTeam, 0.7F, 0.3F);
        selectedTeam.setMinuteChance(minuteChance);
    }

}
