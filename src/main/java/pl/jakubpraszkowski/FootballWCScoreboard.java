package pl.jakubpraszkowski;

import pl.jakubpraszkowski.exception.MatchNotFoundException;
import pl.jakubpraszkowski.exception.NegativeScoreException;
import pl.jakubpraszkowski.exception.SameTeamException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FootballWCScoreboard {
    private final List<Match> matches = new ArrayList<>();

    public Match startGame(String homeTeam, String awayTeam) {
        if (homeTeam.equals(awayTeam)) {
            throw new SameTeamException(homeTeam, awayTeam);
        }

        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    public List<Match> getSummary() {
        return matches.stream()
                .sorted((m1, m2) -> {
                    int total1 = m1.getHomeScore() + m1.getAwayScore();
                    int total2 = m2.getHomeScore() + m2.getAwayScore();

                    if (total1 != total2) {
                        return Integer.compare(total2, total1);
                    }
                    return Integer.compare(matches.indexOf(m2), matches.indexOf(m1));
                })
                .collect(Collectors.toList());
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new NegativeScoreException(homeScore, awayScore);
        }

        findMatch(homeTeam, awayTeam).setScore(homeScore, awayScore);
    }

    public void finishGame(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        matches.remove(match);
    }

    public String displayScoreboard() {
        if (matches.isEmpty()) {
            return "No matches in table yet!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s | %-15s | %-10s | %-10s | %-5s%n",
                "Home", "Away", "HomeScore", "AwayScore", "Total"));
        sb.append("-------------------------------------------------------------\n");

        for (var m : getSummary()) {
            sb.append(String.format("%-15s | %-15s | %-10d | %-10d | %-5d%n",
                    m.getHomeTeam(),
                    m.getAwayTeam(),
                    m.getHomeScore(),
                    m.getAwayScore(),
                    m.getHomeScore() + m.getAwayScore()));
        }

        return sb.toString();
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(() -> new MatchNotFoundException(homeTeam, awayTeam));
    }
}
