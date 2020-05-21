package JavaAutomationLab.censorialchat.io.interfaces;

import JavaAutomationLab.censorialchat.io.exception.UnableToWriteException;

public interface StreamWriter {
    void write(String path, Packable pkg) throws UnableToWriteException, UnableToWriteException;
}
