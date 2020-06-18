package autolab.censorialchat.config.configurator;

import autolab.censorialchat.config.ClientConfig;
import autolab.censorialchat.config.ServerConfig;
import autolab.censorialchat.constant.ConfigConstant;
import autolab.censorialchat.constant.SplitConstant;
import autolab.censorialchat.io.exception.UnableToReadException;
import autolab.censorialchat.io.exception.WrongConfigurationException;
import autolab.censorialchat.util.PropertyArgResolver;
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

    public ServerConfig serverConfig() {
        ServerConfig config = new ServerConfig(host, port, messagePath, availableClients);
        LOGGER.debug(String.format("\nInit server configuration %s", config));
        return config;
    }

    public ClientConfig clientConfig() {
        ClientConfig config = new ClientConfig(host, port, messagePath, token);
        LOGGER.debug(String.format("\nInit client configuration %s", config));
        return config;
    }
}
