package pl.jakubpraszkowski;

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
