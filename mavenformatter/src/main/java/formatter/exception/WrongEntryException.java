package formatter.exception;

public class WrongEntryException extends Exception {
    public WrongEntryException(String message) {super(message);}

    public WrongEntryException() {
        System.out.println("You entered a wrong symbol. Please try again.");
    }
}
