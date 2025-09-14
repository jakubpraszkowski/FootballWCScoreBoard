package pl.jakubpraszkowski.exception;

public class NegativeScoreException extends RuntimeException {
    public NegativeScoreException(String message) {
        super(message);
    }

    public NegativeScoreException(int homeScore, int awayScore) {
        super(String.format("Scores cannot be negative: home=%d, away=%d", homeScore, awayScore));
    }
}
