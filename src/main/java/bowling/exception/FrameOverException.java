package bowling.exception;

public class FrameOverException extends BowlingException {
    public FrameOverException() {
    }

    public FrameOverException(String message) {
        super(message);
    }

    public FrameOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameOverException(Throwable cause) {
        super(cause);
    }

    protected FrameOverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
