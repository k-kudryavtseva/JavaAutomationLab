package autolab.censorialchat.config.base;

public class BaseConfig {
    protected String host;
    protected int port;
    protected String messagePath;

    public BaseConfig(String host, int port, String messagePath) {
        this.host = host;
        this.port = port;
        this.messagePath = messagePath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMessagePath() {
        return messagePath;
    }

    public void setMessagePath(String messagePath) {
        this.messagePath = messagePath;
    }
}
