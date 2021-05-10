package codesquad.team7.baseball.game;

import codesquad.team7.baseball.team.Team;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashMap;
import java.util.Map;

public class TeamInformationMap {

    @MappedCollection(idColumn = "game_id", keyColumn = "baseball_game_team")
    private final Map<TeamEnum, BaseballGameTeamInformation> teamInformationMap;

    TeamInformationMap(Map<TeamEnum, BaseballGameTeamInformation> teamInformationMap) {
        this.teamInformationMap = teamInformationMap;
    }

    public BaseballGameTeamInformation getHome() {
        return teamInformationMap.get(TeamEnum.HOME);
    }

    public BaseballGameTeamInformation getAway() {
        return teamInformationMap.get(TeamEnum.AWAY);
    }

    public Integer getBatter(TeamEnum teamEnum) {
        BaseballGameTeamInformation teamInformation = teamInformationMap.get(teamEnum);
        return teamInformation.getBatter();
    }

    public static TeamInformationMap newTeamInformation(Team home, Team away) {
        Map<TeamEnum, BaseballGameTeamInformation> teamInformationMap = new HashMap<>();
        teamInformationMap.put(TeamEnum.HOME, BaseballGameTeamInformation.newTeamInfo(home));
        teamInformationMap.put(TeamEnum.AWAY, BaseballGameTeamInformation.newTeamInfo(away));
        return new TeamInformationMap(teamInformationMap);
    }

    public void hit(TeamEnum attackTeam) {
        BaseballGameTeamInformation attackTeamInformation = teamInformationMap.get(attackTeam);
        attackTeamInformation.hit();
    }

    public void pitch(TeamEnum defenceTeam) {
        BaseballGameTeamInformation defenceTeamInformation = teamInformationMap.get(defenceTeam);
        defenceTeamInformation.pitch();
    }

    public void scoreUp(TeamEnum attackTeam, int currentInning) {
        BaseballGameTeamInformation attackTeamInformation = teamInformationMap.get(attackTeam);
        attackTeamInformation.scoreUp(currentInning);
    }

    public void setNextBatter(TeamEnum attackTeam) {
        BaseballGameTeamInformation attackTeamInformation = teamInformationMap.get(attackTeam);
        attackTeamInformation.setNextBatter();
    }
}
