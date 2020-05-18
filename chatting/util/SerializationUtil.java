package JavaAutomationLab.chatting.util;

import JavaAutomationLab.chatting.io.interfaces.Packable;
import JavaAutomationLab.chatting.io.exception.UnableToReadException;
import JavaAutomationLab.chatting.io.exception.UnableToWriteException;
import JavaAutomationLab.chatting.io.impl.stream.ObjectReader;
import JavaAutomationLab.chatting.io.impl.stream.ObjectWriter;

public class SerializationUtil {
    private String dataFilePath = System.getProperty("user.dir") + "\\src\\JavaAutomationLab\\serial";
    private String dataFileResponse = System.getProperty("user.dir") + "\\src\\JavaAutomationLab\\serial_response";

    private ObjectReader objectReader;
    private ObjectReader objectReaderResponse;

    public SerializationUtil(String dataFilePath, String dataFileResponse) {
        this.dataFilePath = dataFilePath;
        this.dataFileResponse = dataFileResponse;

        this.objectReader = new ObjectReader(dataFilePath);
        this.objectReaderResponse = new ObjectReader(dataFileResponse);
    }


    public void writeObject(Packable obj) {
        try {
            new ObjectWriter().write(dataFilePath, obj);
        } catch (UnableToWriteException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("%s is unable to write!", dataFilePath));
        }
    }

    public Packable readObject() {
        try {
            return objectReader.read();
        } catch (UnableToReadException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("%s is unable to read!", dataFilePath));
        }

    }

    public Packable readResponse() {
        try {
            return objectReaderResponse.read();
        } catch (UnableToReadException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("%s is unable to read!", dataFileResponse));
        }

    }

    public void writeResponse(Packable obj) {
        try {
            new ObjectWriter().write(dataFileResponse, obj);
        } catch (UnableToWriteException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("%s is unable to write!", dataFileResponse));
        }
    }

}
