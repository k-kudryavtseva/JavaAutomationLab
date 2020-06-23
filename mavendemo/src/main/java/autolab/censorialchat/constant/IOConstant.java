package autolab.censorialchat.constant;

public class IOConstant {
    public static final String CWD = System.getProperty("user.dir");
    public static final String PATH_SEPARATOR = System.getProperty("file.separator");
    public static final String CONFIG_PATH = String.format("%s%s%s%s%s%s%s%s%s",
            CWD, PATH_SEPARATOR, "src", PATH_SEPARATOR, "main", PATH_SEPARATOR, "resources", PATH_SEPARATOR, "config.properties");
    public static final String XML = ".xml";
}
