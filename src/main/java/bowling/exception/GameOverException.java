package bowling.exception;

public class GameOverException extends BowlingException {
    public GameOverException() {
    }

    public GameOverException(String message) {
        super(message);
    }

    public GameOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameOverException(Throwable cause) {
        super(cause);
    }

    protected GameOverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
