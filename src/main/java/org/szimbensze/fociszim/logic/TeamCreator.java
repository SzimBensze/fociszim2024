package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Formation;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;

public class TeamCreator {

    UserInput userInput = new UserInput();

    public Team createTeam(Class teamType) throws IncorrectTeamTypeException {
        if (teamType.equals(Home.class)) return new Home();
        else if (teamType.equals(Visitor.class)) return new Visitor();
        else throw new IncorrectTeamTypeException("Incorrect team type specified!");
    }

    public void addStats(Team selectedTeam) {
        selectedTeam.setName(userInput.inputName(selectedTeam));
        addPoints(selectedTeam, userInput.inputPoints(selectedTeam.getName(), "atk"),
            userInput.inputPoints(selectedTeam.getName(), "mid"),
            userInput.inputPoints(selectedTeam.getName(), "def"));
    }

    private void addPoints(Team selectedTeam, Integer atk, Integer mid, Integer def) {
        selectedTeam.setAtk(atk);
        selectedTeam.setMid(mid);
        selectedTeam.setDef(def);
    }

    public void addFormation(Team selectedTeam, Formation form) {
        selectedTeam.setFormation(form);
    }

}
