package autolab.censorialchat.io.interfaces;

import autolab.censorialchat.io.exception.UnableToReadException;

public interface StreamReader {
    Packable read() throws UnableToReadException;
}
