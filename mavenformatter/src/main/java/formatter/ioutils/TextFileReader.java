package formatter.ioutils;

import formatter.exception.UnableToCloseException;
import formatter.exception.UnableToReadException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader extends BaseReader {


    public TextFileReader(File file) {
        super(file);
    }

    public TextFileReader(String path) {
        super(path);
    }

    public String read() throws UnableToReadException, UnableToCloseException {
        BufferedReader objReader = null;
        StringBuilder sb = new StringBuilder("");
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader(this.path));

            while ((strCurrentLine = objReader.readLine()) != null) {

                sb.append(strCurrentLine + "\n");
            }
            return sb.toString();

        } catch (IOException e) {

            e.printStackTrace();
            // throw smth??

        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                // ex.printStackTrace();
                throw new UnableToCloseException("Unable to close");
            }
        }
        throw new UnableToReadException("Unable to read");
    }
}
