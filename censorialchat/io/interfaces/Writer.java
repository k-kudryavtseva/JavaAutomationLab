package JavaAutomationLab.censorialchat.io.interfaces;

import JavaAutomationLab.censorialchat.io.exception.UnableToWriteException;

public interface Writer {
    void write(String path, String text) throws UnableToWriteException, JavaAutomationLab.censorialchat.io.exception.UnableToWriteException;
    void append(String path, String text) throws UnableToWriteException, JavaAutomationLab.censorialchat.io.exception.UnableToWriteException;
}
