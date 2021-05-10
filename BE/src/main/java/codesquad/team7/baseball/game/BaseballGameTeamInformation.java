package codesquad.team7.baseball.game;

import codesquad.team7.baseball.team.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.List;
import java.util.stream.Collectors;

public class BaseballGameTeamInformation {

    @Id
    @Column("information_id")
    // TeamInformation 과 PlayersStatistics / TeamScores 를 OneToMany Mapping 하기 위해 추가한 id
    private final Long id;

    private final Long teamId;

    @Embedded.Empty
    private final PlayersStatistics playersStatistics;
    @Embedded.Empty
    private final TeamScores teamScores;
    private Integer batterNumber;
    private Integer pitches;

    BaseballGameTeamInformation(Long id, Long teamId, PlayersStatistics playersStatistics, Integer batterNumber, Integer pitches, TeamScores teamScores) {
        this.id = id;
        this.teamId = teamId;
        this.playersStatistics = playersStatistics;
        this.batterNumber = batterNumber;
        this.pitches = pitches;
        this.teamScores = teamScores;
    }

    public static BaseballGameTeamInformation newTeamInfo(Team team) {
        return new BaseballGameTeamInformation(
                null,
                team.getId(),
                PlayersStatistics.newStatistics(),
                0,
                0,
                TeamScores.newTeamScores()
        );
    }

    public void hit() {
        playersStatistics.hit(batterNumber);
    }

    public void out() {
        playersStatistics.out(batterNumber);
    }

    public void setNextBatter() {
        batterNumber = (batterNumber + 1) % playersStatistics.size();
    }

    public void pitch() {
        pitches++;
    }

    public void scoreUp(int inning) {
        teamScores.scoreUp(inning);
    }

    public Long getTeamId() {
        return teamId;
    }

    public Integer getTotalScore() {
        return teamScores.getTotalScore();
    }

    public PlayersStatistics getPlayersStatistics() {
        return playersStatistics;
    }

    public Integer getBatter() {
        return batterNumber;
    }

    public Integer getPitches() {
        return pitches;
    }

    public List<Integer> getInningScore() {
        return teamScores.getInningScore().stream()
                .map(InningScore::getInningScore)
                .collect(Collectors.toList());
    }

    public void nextInning() {
        teamScores.nextInning();
    }
}