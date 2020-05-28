package autolab.censorialchat.io.interfaces;

import autolab.censorialchat.io.exception.UnableToWriteException;

public interface StreamWriter {
    void write(String path, Packable pkg) throws UnableToWriteException;
}
