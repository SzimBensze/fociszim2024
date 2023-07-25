package org.szimbensze.fociszim.logic;

import org.szimbensze.fociszim.model.Formation;
import org.szimbensze.fociszim.model.Home;
import org.szimbensze.fociszim.model.Team;
import org.szimbensze.fociszim.model.Visitor;

public class TeamCreator {

    public static Team createTeam(Class teamType) throws NoTeamSpecifiedException {
        if (teamType.equals(Home.class)) return new Home();
        else if (teamType.equals(Visitor.class)) return new Visitor();
        else throw new NoTeamSpecifiedException("Home or visitor team only!");
    }

    public static void addStats(Team selectedTeam) {
        selectedTeam.setAtk(5);
        selectedTeam.setMid(6);
        selectedTeam.setDef(7);
    }

    public static void addFormation(Team selectedTeam) {
        selectedTeam.setFormation(Formation.F451);
    }

}
