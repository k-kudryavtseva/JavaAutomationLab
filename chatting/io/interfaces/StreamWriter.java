package JavaAutomationLab.chatting.io.interfaces;

import JavaAutomationLab.chatting.io.exception.UnableToWriteException;

public interface StreamWriter {
    void write(String path, Packable pkg) throws UnableToWriteException, UnableToWriteException;
}
