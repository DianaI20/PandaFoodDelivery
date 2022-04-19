package ro.utcluj.pandafooddelivery.service.exception;

public class WrongPasswordException extends Exception {

    public WrongPasswordException(String message) {
        super(message);
    }
}
