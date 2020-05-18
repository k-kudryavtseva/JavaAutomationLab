package JavaAutomationLab.chatting.io.impl.stream;

import JavaAutomationLab.chatting.io.base.BaseReader;
import JavaAutomationLab.chatting.io.exception.UnableToReadException;
import JavaAutomationLab.chatting.io.interfaces.Packable;
import JavaAutomationLab.chatting.io.interfaces.StreamReader;

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
