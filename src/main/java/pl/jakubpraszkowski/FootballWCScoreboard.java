package pl.jakubpraszkowski;

import java.util.ArrayList;
import java.util.List;

public class FootballWCScoreboard {
    private final List<Match> matches = new ArrayList<>();

    public Match startGame(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    public List<Match> getSummary() {
        return new ArrayList<>(matches);
    }
}
