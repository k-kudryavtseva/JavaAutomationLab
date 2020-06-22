package autolab.censorialchat.classes.mainclasses;

import autolab.censorialchat.config.ClientConfig;
import autolab.censorialchat.config.configurator.BaseConfigurator;
import autolab.censorialchat.dao.impl.MessageDAOImpl;
import org.apache.log4j.BasicConfigurator;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Client {
    public static void main(String[] args) throws InterruptedException {
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
    private UUID uuid;
    MessageDAOImpl messageDAOImpl = new MessageDAOImpl();

    public Client() throws InterruptedException {
        this.scanner = new Scanner(System.in);
        uuid = UUID.randomUUID();

        Listener listener = new Listener();
        listener.start();

        String str = "";
        while (!str.equalsIgnoreCase("quit")) {
            str = scanner.nextLine();
            Message msg = new Message(HOST, PORT, TOKEN, str, uuid.toString(), new Date(), 0, null);
            messageDAOImpl.create(msg);
            Thread.sleep(10000);
            Message processedmsg = messageDAOImpl.getMessageByID(messageDAOImpl.getLastMessageID());
            System.out.println(processedmsg.getProcessedMsg());
        }
    }

    private class Listener extends Thread {
        private boolean stop;
        public void setStop() {
            stop = true;
        }
    }
}
