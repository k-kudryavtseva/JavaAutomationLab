package autolab.censorialchat.io.interfaces;

import autolab.censorialchat.io.exception.UnableToCloseException;
import autolab.censorialchat.io.exception.UnableToReadException;

public interface Reader {
    String read() throws UnableToReadException, UnableToCloseException;
}
