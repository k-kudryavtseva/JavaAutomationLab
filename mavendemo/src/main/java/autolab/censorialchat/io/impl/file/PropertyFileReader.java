package autolab.censorialchat.io.impl.file;

import autolab.censorialchat.io.base.BaseReader;
import autolab.censorialchat.io.exception.UnableToReadException;
import autolab.censorialchat.io.interfaces.Reader;

import java.io.*;
import java.util.Properties;

public class PropertyFileReader extends BaseReader implements Reader {
    private Properties props;

    public PropertyFileReader(File file) {
        super(file);
    }

    public PropertyFileReader(String path) {
        super(path);
    }

    public String read() throws UnableToReadException {
        this.props = new Properties();
        try {
            if (!this.path.endsWith(".properties")) {
                throw new RuntimeException("Provide the io in proper format");
            }
            InputStream inputStream = new FileInputStream(new File(this.path).getAbsolutePath());
            this.props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnableToReadException("");
        }
        return "OK"; // :)
    }

    public String getPropertyValue(String key) {
        return this.props.getProperty(key);
    }
}
