package pl.jakubpraszkowski;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jakubpraszkowski.exception.MatchNotFoundException;
import pl.jakubpraszkowski.exception.NegativeScoreException;
import pl.jakubpraszkowski.exception.SameTeamException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FootballWCScoreboardTest {
    private FootballWCScoreboard board;

    @BeforeEach
    void setUp() {
        board = new FootballWCScoreboard();
    }

    @Test
    void startGameShouldInitializeWithZeroScoreTest() {
        Match match = board.startGame("Mexico", "Canada");

        assertEquals("Mexico", match.getHomeTeam());
        assertEquals("Canada", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void updateScoreShouldChangeMatchResultTest() {
        board.startGame("Mexico", "Canada");
        board.updateScore("Mexico", "Canada", 1, 2);

        List<Match> summary = board.getSummary();
        Match match = summary.get(0);

        assertEquals(1, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }

    @Test
    void finishGameShouldRemoveMatchTest() {
        board.startGame("Spain", "Brazil");
        board.finishGame("Spain", "Brazil");
        assertTrue(board.getSummary().isEmpty());
    }

    @Test
    void getSummaryShouldOrderByTotalScoreThenCreationTimeTest() {
        board.startGame("Mexico", "Canada");
        board.updateScore("Mexico", "Canada", 0, 5);

        board.startGame("Spain", "Brazil");
        board.updateScore("Spain", "Brazil", 10, 2);

        board.startGame("Germany", "France");
        board.updateScore("Germany", "France", 2, 2);

        board.startGame("Uruguay", "Italy");
        board.updateScore("Uruguay", "Italy", 6, 6);

        board.startGame("Argentina", "Australia");
        board.updateScore("Argentina", "Australia", 3, 1);

        var summary = board.getSummary();

        assertAll("Summary ordering",
                () -> assertEquals("Uruguay 6 - Italy 6", summary.get(0).toString()),
                () -> assertEquals("Spain 10 - Brazil 2", summary.get(1).toString()),
                () -> assertEquals("Mexico 0 - Canada 5", summary.get(2).toString()),
                () -> assertEquals("Argentina 3 - Australia 1", summary.get(3).toString()),
                () -> assertEquals("Germany 2 - France 2", summary.get(4).toString())
        );
    }

    @Test
    void cannotStartGameWithSameTeamsTest() {
        assertThrows(SameTeamException.class,
                () -> board.startGame("Spain", "Spain"));
    }

    @Test
    void updateNonExistingGameThrowsTest() {
        assertThrows(MatchNotFoundException.class,
                () -> board.updateScore("Non", "Existent", 1, 1));
    }

    @Test
    void negativeScoresAreRejectedTest() {
        board.startGame("A", "B");
        assertThrows(NegativeScoreException.class,
                () -> board.updateScore("A", "B", -1, 0));
        assertThrows(NegativeScoreException.class,
                () -> board.updateScore("A", "B", 0, -1));
    }

    @Test
    void displayScoreboardShouldReturnFormattedTable() {
        board.startGame("Mexico", "Canada");
        board.updateScore("Mexico", "Canada", 0, 5);

        board.startGame("Spain", "Brazil");
        board.updateScore("Spain", "Brazil", 10, 2);

        String output = board.displayScoreboard();

        String[] expectedStrings = {"Mexico", "Canada", "Spain", "Brazil", "HomeScore", "AwayScore", "Total"};
        for (var s : expectedStrings) {
            assertTrue(output.contains(s), "Output should contain: " + s);
        }
    }
}
