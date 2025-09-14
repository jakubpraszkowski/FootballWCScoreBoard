package pl.jakubpraszkowski.exception;

public class NegativeScoreException extends RuntimeException {
    public NegativeScoreException(String message) {
        super(message);
    }
}
