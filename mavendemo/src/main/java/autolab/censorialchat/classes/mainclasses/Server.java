package autolab.censorialchat.classes.mainclasses;

import autolab.censorialchat.config.ServerConfig;
import autolab.censorialchat.config.configurator.BaseConfigurator;
import autolab.censorialchat.constant.IOConstant;
import autolab.censorialchat.filters.*;
import autolab.censorialchat.io.exception.UnableToReadException;
import autolab.censorialchat.io.xmlutils.XMLMarshaller;
import autolab.censorialchat.io.xmlutils.XMLUnmarshaller;
import autolab.censorialchat.io.xmlutils.XMLUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static autolab.censorialchat.constant.ConfigConstant.TOKEN;

public class Server {
    private final static Logger LOGGER = Logger.getLogger(Server.class);
    private static ArrayList<IFilter> filterList;
    private static XMLMarshaller xmlMarshaller;
    private static XMLUnmarshaller xmlUnmarshaller;

    public static void main(String[] args) throws InterruptedException {
        BasicConfigurator.configure();
        initFilters();
        listen();
    }

    private final List<Connection> connections = Collections.synchronizedList(new ArrayList<>());
    private static final List<Message> chatHistory = Collections.synchronizedList(new ArrayList<>());
    private ServerSocket server;

    private static ServerConfig config = init();

    private static final String HOST = config.getHost();
    private static final int PORT = config.getPort();
    private static final String PATH_TO_LISTEN = config.getMessagePath();
    private static final Set<String> AVAILABLE_CLIENTS = config.getAvailableClients();

    public List<Connection> getConnections() {
        return connections;
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getHOST() {
        return HOST;
    }

    public static XMLMarshaller getXmlMarshaller() {
        return xmlMarshaller;
    }

    private static void initFilters() {
        try {

            JAXBContext context = JAXBContext.newInstance(Message.class);
            xmlMarshaller = new XMLMarshaller(context);
            xmlUnmarshaller = new XMLUnmarshaller(context);

            filterList = new ArrayList<>();
            filterList.add(new EmojiFilter());
            filterList.add(new NERPersonFilter());
            filterList.add(new NERLocationFilter());
            filterList.add(new CensorialFilter());
            filterList.add(new SpaceFilter());
            filterList.add(new CapitalizeFilter());

            CensorialFilter.readBadWords();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    private static ServerConfig init() {
        return BaseConfigurator.getInstance().serverConfig();
    }

    public Server() {
    }

    static void listen() throws InterruptedException {

        LOGGER.info("server up");

        while (true) {
            String[] fileLs = new File(PATH_TO_LISTEN).getAbsoluteFile().list();
            assert fileLs != null;
            int numOfMsgs = fileLs.length;
            if (numOfMsgs > 0) {
                // parallel log and push in history numOfMsgs at one moment
                for (String filePath : fileLs) {
                    Connection conn = new Connection(PATH_TO_LISTEN + IOConstant.PATH_SEPARATOR + filePath, filterList);
                    conn.run();
                }
            }
            Thread.sleep(10000);
        }
    }

    static class Connection extends Thread {

        private String path;
        private List<IFilter> filterList;

        public Connection(String path, List<IFilter> filterList) {
            this.path = path;
            this.filterList = filterList;
        }

        public void run() {
            try {
                Message msg = readMessage(path);
                if (msg != null) {
                    File file = new File(path);
                    file.delete();

                    String processedMessage = msg.getMsg();

                    if (processedMessage.equalsIgnoreCase("quit")) {
                        processedMessage = "Somebody quitted";
                    } else {
                        for (IFilter filterObj : filterList) {
                            processedMessage = filterObj.filter(processedMessage);
                        }

                        processedMessage = msg.getDate().toString() + ": " + processedMessage;
                    }

                    LOGGER.info(processedMessage);
                    msg.setMsg(processedMessage);
                    chatHistory.add(msg);
                }
            } catch (UnableToReadException e) {
                e.printStackTrace();
                currentThread().interrupt();
            }
        }
    }

    private static Message readMessage(String pathTo) throws UnableToReadException {
        try {
            return XMLUtil.readMessage(pathTo);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
