package JavaAutomationLab.censorialchat.io.interfaces;

import JavaAutomationLab.censorialchat.io.exception.UnableToCloseException;
import JavaAutomationLab.censorialchat.io.exception.UnableToReadException;

public interface Reader {
    String read() throws UnableToReadException, UnableToCloseException, JavaAutomationLab.censorialchat.io.exception.UnableToCloseException, JavaAutomationLab.censorialchat.io.exception.UnableToReadException;
}
