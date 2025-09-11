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

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        findMatch(homeTeam, awayTeam).setScore(homeScore, awayScore);
    }

    public void finishGame(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        matches.remove(match);
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(() -> new MatchNotFoundException(homeTeam, awayTeam));
    }
}
