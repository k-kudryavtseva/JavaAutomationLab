package formatter.config;

import formatter.constant.ConfigConstant;
import formatter.constant.SplitConstant;
import formatter.exception.UnableToReadException;
import formatter.exception.WrongConfigurationException;
import formatter.ioutils.PropertyArgResolver;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseConfigurator {
    private final static Logger LOGGER = Logger.getLogger(BaseConfigurator.class);
    private static BaseConfigurator instance;
    private PropertyArgResolver config;

    private String host;
    private int port;
    private String token;
    private Set<String> availableClients;
    private String messagePath;

    public static BaseConfigurator getInstance() {
        if (instance == null) {
            synchronized (BaseConfigurator.class) {
                if (instance == null) {
                    instance = new BaseConfigurator();
                }
            }
        }
        return instance;
    }

    private BaseConfigurator() {
        try {
            config = PropertyArgResolver.getInstance();
            host = config.get(ConfigConstant.HOSTNAME);
            port = Integer.valueOf(config.get(ConfigConstant.PORT));
            messagePath = config.get(ConfigConstant.MESSAGE_PATH);
            availableClients =
                    Arrays.stream(
                            config.get(ConfigConstant.AVAILABLE_CLIENTS).split(SplitConstant.COLON))
                            .collect(Collectors.toSet()
                            );
            token = config.get(ConfigConstant.TOKEN);
        } catch (UnableToReadException ioe) {
            ioe.printStackTrace();
            throw new WrongConfigurationException("Something went wrong while configuring!");
        }
    }
}
