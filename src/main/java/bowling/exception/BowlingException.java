package bowling.exception;

public class BowlingException extends Exception {
    public BowlingException() {
    }

    public BowlingException(String message) {
        super(message);
    }

    public BowlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BowlingException(Throwable cause) {
        super(cause);
    }

    protected BowlingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
