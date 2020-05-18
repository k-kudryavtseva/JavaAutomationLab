package JavaAutomationLab.chatting.util;

import JavaAutomationLab.chatting.io.exception.UnableToReadException;
import JavaAutomationLab.chatting.io.impl.file.PropertyFileReader;

public class PropertyUtil {
    private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "\\src\\JavaAutomationLab\\config.properties";
    private static final PropertyFileReader READER = new PropertyFileReader(CONFIG_FILE_PATH);
    static {
        try {
            READER.read();
        } catch (UnableToReadException e) {
            e.printStackTrace();
        }
    }

    public static String getValueByKey(String key) {
        return READER.getPropertyValue(key);
    }
}
