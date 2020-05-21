package JavaAutomationLab.censorialchat.io.interfaces;

import JavaAutomationLab.censorialchat.io.exception.UnableToReadException;

public interface StreamReader {
    Packable read() throws UnableToReadException, UnableToReadException;
}
