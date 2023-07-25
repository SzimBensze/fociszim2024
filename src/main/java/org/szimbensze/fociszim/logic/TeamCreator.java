package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Formation;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;

public class TeamCreator {

    public static Team createTeam(Class teamType) throws IncorrectTeamTypeException {
        if (teamType.equals(Home.class)) return new Home();
        else if (teamType.equals(Visitor.class)) return new Visitor();
        else throw new IncorrectTeamTypeException("Incorrect team type specified!");
    }

    public static void addStats(Team selectedTeam, String name, Integer atk, Integer mid, Integer def) {
        selectedTeam.setName(name);
        selectedTeam.setAtk(atk);
        selectedTeam.setMid(mid);
        selectedTeam.setDef(def);
    }

    public static void addFormation(Team selectedTeam, Formation form) {
        selectedTeam.setFormation(form);
    }

}
