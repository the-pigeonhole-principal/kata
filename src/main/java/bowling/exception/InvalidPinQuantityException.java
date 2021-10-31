package bowling.exception;

public class InvalidPinQuantityException extends BowlingException {
    public InvalidPinQuantityException() {
    }

    public InvalidPinQuantityException(String message) {
        super(message);
    }

    public InvalidPinQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPinQuantityException(Throwable cause) {
        super(cause);
    }

    protected InvalidPinQuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
