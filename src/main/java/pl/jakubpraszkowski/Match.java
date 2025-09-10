package pl.jakubpraszkowski;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Match {
    private final String homeTeam;
    private final String awayTeam;

    @Setter
    private int homeScore;

    @Setter
    private int awayScore;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }
}
