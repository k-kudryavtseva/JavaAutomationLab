package autolab.censorialchat.io.impl.stream;

import autolab.censorialchat.io.base.BaseReader;
import autolab.censorialchat.io.exception.UnableToReadException;
import autolab.censorialchat.io.interfaces.Packable;
import autolab.censorialchat.io.interfaces.StreamReader;

import java.io.*;

public class ObjectReader extends BaseReader implements StreamReader {
    public ObjectReader(File file) {
        super(file);
    }

    public ObjectReader(String path) {
        super(path);
    }

    @Override
    public Packable read() throws UnableToReadException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file.getAbsoluteFile()))) {
            return (Packable) ois.readObject();
        } catch (EOFException e) {
            // do nothing :)
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new UnableToReadException("Could not read object!");
        }
    }
}
