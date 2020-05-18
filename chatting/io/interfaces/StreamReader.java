package JavaAutomationLab.chatting.io.interfaces;

import JavaAutomationLab.chatting.io.exception.UnableToReadException;

public interface StreamReader {
    Packable read() throws UnableToReadException, UnableToReadException;
}
