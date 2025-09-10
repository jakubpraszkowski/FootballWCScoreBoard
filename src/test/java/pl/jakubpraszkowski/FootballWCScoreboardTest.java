package pl.jakubpraszkowski;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FootballWCScoreboardTest {
    private FootballWCScoreboard board;

    @BeforeEach
    void setUp() {
        board = new FootballWCScoreboard();
    }

    @Test
    void startGameShouldInitializeWithZeroScore() {
        Match match = board.startGame("Mexico", "Canada");

        assertEquals("Mexico", match.getHomeTeam());
        assertEquals("Canada", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void updateScoreShouldChangeMatchResult() {
        board.startGame("Mexico", "Canada");
        board.updateScore("Mexico", "Canada", 1, 2);

        List<Match> summary = board.getSummary();
        Match match = summary.get(0);

        assertEquals(1, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }
}
