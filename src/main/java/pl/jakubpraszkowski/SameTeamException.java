package pl.jakubpraszkowski;

public class SameTeamException extends RuntimeException {
    public SameTeamException(String message) {
        super(message);
    }

    public SameTeamException(String homeTeam, String awayTeam) {
        super(String.format("Home and away teams cannot be the same: %s vs %s", homeTeam, awayTeam));
    }
}
