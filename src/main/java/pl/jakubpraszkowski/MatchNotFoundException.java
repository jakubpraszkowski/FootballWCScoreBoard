package pl.jakubpraszkowski;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(String message) {
        super(message);
    }

    public MatchNotFoundException(String homeTeam, String awayTeam) {
        super(String.format("No such game: %s vs %s", homeTeam, awayTeam));
    }
}
