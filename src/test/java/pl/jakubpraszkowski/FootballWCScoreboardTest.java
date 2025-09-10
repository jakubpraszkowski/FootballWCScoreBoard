package pl.jakubpraszkowski;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
