package autolab.censorialchat.classes.mainclasses;

import autolab.censorialchat.config.ClientConfig;
import autolab.censorialchat.config.configurator.BaseConfigurator;
import autolab.censorialchat.dao.impl.MessageDAOImpl;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Client implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(Client.class);

    private UUID uuid;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new Client();
    }

    private static ClientConfig config = init();

    private static ClientConfig init() {
        return BaseConfigurator.getInstance().clientConfig();
    }

    private static final String HOST = config.getHost();
    private static final int PORT = config.getPort();
    private static final String PATH_TO_PUSH = config.getMessagePath();
    private static final String TOKEN = config.getToken();

    private final Scanner scanner;

    MessageDAOImpl messageDAOImpl = new MessageDAOImpl();

    public Client() {
        this.scanner = new Scanner(System.in);

        uuid = UUID.randomUUID();

        Listener listener = new Listener();
        listener.start();

        String str = "";
        while (!str.equalsIgnoreCase("quit")) {
            str = scanner.nextLine();
            Message msg = new Message(HOST, PORT, TOKEN, str, uuid.toString(), new Date(), 0, null);
            messageDAOImpl.create(msg);
        }
    }

    private void initClient() {

        Message msg = new Message(
                HOST,
                PORT,
                TOKEN,
                String.format("%s client connected to %s:%s", uuid.toString(), HOST, PORT),
                uuid.toString(),
                new Date(),
                0,
                null
        );

        messageDAOImpl.create(msg);
        LOGGER.info(String.format("Connected to %s:%s", HOST, PORT));
    }

    @Override
    public void run() {

        Listener listener = new Listener();
        listener.start();

        String str = "";
        while (!str.equalsIgnoreCase("quit")) {
            str = scanner.nextLine();
            Message msg = new Message(HOST, PORT, TOKEN, str, uuid.toString(), new Date(), 0, null);
            messageDAOImpl.create(msg);
        }
    }

    private class Listener extends Thread {
        private boolean stop;
        public void setStop() {
            stop = true;
        }
    }
}
