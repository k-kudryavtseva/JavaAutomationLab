package autolab.censorialchat.classes.mainclasses;

import autolab.censorialchat.config.ClientConfig;
import autolab.censorialchat.config.configurator.BaseConfigurator;
import autolab.censorialchat.constant.IOConstant;
import autolab.censorialchat.dao.impl.MessageDAOImpl;
import autolab.censorialchat.io.exception.UnableToWriteException;
import autolab.censorialchat.io.xmlutils.XMLMarshaller;
import autolab.censorialchat.io.xmlutils.XMLUnmarshaller;
import autolab.censorialchat.io.xmlutils.XMLUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Client implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(Client.class);

    private static XMLMarshaller xmlMarshaller;
    private static XMLUnmarshaller xmlUnmarshaller;

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

    private BufferedReader in;
    private PrintWriter out;
    private final Scanner scanner;

    MessageDAOImpl messageDAO = new MessageDAOImpl();

    public static XMLMarshaller getXmlMarshaller() {
        return xmlMarshaller;
    }

    public Client() {
        this.scanner = new Scanner(System.in);

        uuid = UUID.randomUUID();

        try {
            try {
                initClient();

                Listener listener = new Listener();
                listener.start();

                String str = "";
                while (!str.equalsIgnoreCase("quit")) {
                    str = scanner.nextLine();
                    //out.println(str);
                    //out.println(initMessageOut(str, this));
                    Message msg = new Message(HOST, PORT, TOKEN, str, uuid.toString(), new Date(), 0, null);
                    //writeMessage(msg, uuid.toString());
                    messageDAO.create(msg);
                }

            } finally {
                close();
            }
        } catch (IOException | JAXBException | UnableToWriteException e){
            LOGGER.error("Something went wrong. Reload chat");
            e.printStackTrace();
        }
    }

    private static void writeMessage(Message message, String filename) throws UnableToWriteException {
        try {
            String filePath = PATH_TO_PUSH + IOConstant.PATH_SEPARATOR + filename + IOConstant.XML;
            XMLUtil.writeMessage(filePath, message);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            throw new UnableToWriteException("Something went wrong while writing message!");
        }
    }

    private void initClient() throws IOException, JAXBException, UnableToWriteException {

        JAXBContext context = JAXBContext.newInstance(Message.class);
        xmlMarshaller = new XMLMarshaller(context);
        xmlUnmarshaller = new XMLUnmarshaller(context);

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
        //writeMessage(msg, uuid.toString());

        System.out.println("1");
        messageDAO.create(msg);
        System.out.println("1");

        LOGGER.info(String.format("Connected to %s:%s", HOST, PORT));
    }

    private void close(){
        File clientFile = new File(PATH_TO_PUSH + IOConstant.PATH_SEPARATOR + uuid.toString() + IOConstant.XML);
        clientFile.delete();
        System.exit(-1);
    }

    @Override
    public void run() {

        try {

            initClient();

            Listener listener = new Listener();
            listener.start();

            String str = "";
            while (!str.equalsIgnoreCase("quit")) {
                str = scanner.nextLine();

                Message msg = new Message(HOST, PORT, TOKEN, str, uuid.toString(), new Date(), 0, null);
                //writeMessage(msg, uuid.toString());
                messageDAO.create(msg);
            }

        } catch (IOException | JAXBException | UnableToWriteException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private class Listener extends Thread {
        private boolean stop;

        public void setStop() {
            stop = true;
        }
    }
}
