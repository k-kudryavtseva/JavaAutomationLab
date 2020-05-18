package JavaAutomationLab.chatting.io.interfaces;

import JavaAutomationLab.chatting.io.exception.UnableToCloseException;
import JavaAutomationLab.chatting.io.exception.UnableToReadException;

public interface Reader {
    String read() throws UnableToReadException, UnableToCloseException;
}
