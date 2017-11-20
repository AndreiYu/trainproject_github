package by.poezdrw.exception;

public class WrongElementException extends RuntimeException {


    public WrongElementException() {
        super();
    }

    public WrongElementException(String message) {
        super(message);
    }

    public WrongElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongElementException(Throwable cause) {
        super(cause);
    }
}
