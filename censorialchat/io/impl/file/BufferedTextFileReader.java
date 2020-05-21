package JavaAutomationLab.censorialchat.io.impl.file;

import JavaAutomationLab.censorialchat.io.base.BaseReader;
import JavaAutomationLab.censorialchat.io.exception.UnableToCloseException;
import JavaAutomationLab.censorialchat.io.interfaces.Reader;

import java.io.*;

public class BufferedTextFileReader extends BaseReader implements Reader {

    public BufferedTextFileReader(File file) {
        super(file);
    }

    public BufferedTextFileReader(String path) {
        super(path);
    }

    public String read() throws UnableToCloseException {
        BufferedReader objReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader(this.path));

            while ((strCurrentLine = objReader.readLine()) != null) {

                sb.append(strCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new UnableToCloseException("Unable to close");
            }
        }
        return sb.toString();
    }
}
