package JavaAutomationLab.racing.ioutils;

import JavaAutomationLab.racing.exception.UnableToCloseException;
import JavaAutomationLab.racing.exception.UnableToReadException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader extends BaseReader {
    private Properties props;

    public PropertyFileReader(File file) {
        super(file);
    }

    public PropertyFileReader(String path) {
        super(path);
    }

    public String read() throws UnableToReadException, UnableToCloseException {
        this.props = new Properties();

        InputStream inputStream = null; // to be able to use inputStream variable without "might not have been initialized"

        try {
            if (!this.path.endsWith(".properties")) {
                throw new RuntimeException("Provide the file in proper format");
            }
            inputStream = new FileInputStream(new File(this.path).getAbsolutePath());
            this.props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnableToReadException("");
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                throw new UnableToCloseException("Unable to close");
            }
        }
        return "OK"; // :)
    }

    public String getPropertyValue(String key) {
        return this.props.getProperty(key);
    }
}