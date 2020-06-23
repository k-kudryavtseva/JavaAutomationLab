package formatter.exception;

public class WrongConfigurationException extends RuntimeException{
    public WrongConfigurationException(String message) {
        super(message);
    }
}
