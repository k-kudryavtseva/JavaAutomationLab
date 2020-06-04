package autolab.censorialchat.classes.c10;

import autolab.censorialchat.constant.C10Constant;
import autolab.censorialchat.filters.*;
import autolab.censorialchat.io.xmlutils.XMLMarshaller;
import autolab.censorialchat.io.xmlutils.XMLUnmarshaller;
import autolab.censorialchat.util.PropertyUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static autolab.censorialchat.io.xmlutils.XMLUtils.*;

public class Server {
    private final static Logger LOGGER = Logger.getLogger(Server.class);
    private final static String HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
    private final static int PORT = Integer.parseInt(PropertyUtil.getValueByKey(C10Constant.PORT));
    private final static String TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);
    private ArrayList<IFilter> filtersList;
    private static XMLMarshaller xmlMarshaller;
    private static XMLUnmarshaller xmlUnmarshaller;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new Server().startServer();
    }

    private final List<Connection> connections = Collections.synchronizedList(new ArrayList<>());
    private final List<Message> chatHistory = Collections.synchronizedList(new ArrayList<>());
    private ServerSocket server;

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

    public Server() {
        try {

            JAXBContext context = JAXBContext.newInstance(Message.class);
            xmlMarshaller = new XMLMarshaller(context);
            xmlUnmarshaller = new XMLUnmarshaller(context);

            filtersList = new ArrayList<>();
            filtersList.add(new EmojiFilter());
            filtersList.add(new NERPersonFilter());
            filtersList.add(new NERLocationFilter());
            filtersList.add(new CensorialFilter());
            filtersList.add(new SpaceFilter());
            filtersList.add(new CapitalizeFilter());

            server = new ServerSocket(PORT);

            CensorialFilter.readBadWords();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            LOGGER.info("server up");

            while (true) {
                Socket socket = server.accept();

                Connection connection = new Connection(socket, this);
                connections.add(connection);
                connection.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
            LOGGER.info("server down");
        }
    }

    private void closeAll() {
        try {
            server.close();

            synchronized (connections) {
                for (Connection connection : connections) {
                    connection.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Error closing connections!");
        }
    }

    public class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private final Socket socket;
        private final Server server;

        private String name = "";

        public Connection(Socket socket, Server server) {
            this.socket = socket;
            this.server = server;

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
                close();
            }
        }

        @Override
        public void run() {
            try {

                //name = in.readLine();
                name = getMessageIn(readXml(in), xmlUnmarshaller).getMsg();
                sendMsgForAll(name + " connected");

                chatHistory.sort(Comparator.comparing(Message::getDate));

                sendChatHistory();

                String str = "";
                while (true) {
                    //str = in.readLine();

                    Message msgIn = getMessageIn(readXml(in), xmlUnmarshaller);
                    str = msgIn.getMsg();

                    if (str.equalsIgnoreCase("quit")) break;

                    String processedMessage = str;

                    for (IFilter filterObj : filtersList) {
                        processedMessage = filterObj.filter(processedMessage);
                    }

                    processedMessage = msgIn.getDate().toString() + " " + name + ": " + processedMessage;
                    sendMsgForAll(processedMessage);

                    msgIn.setMsg(processedMessage);

                    chatHistory.add(msgIn);
                }

                sendMsgForAll(name + ": disconnected");
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
        
        private void sendChatHistory() {
            //chatHistory.forEach(historyMsg -> out.println(historyMsg));
            for (Message msg: chatHistory) {
                out.println(initMessageOut(msg.getMsg(), server));
            }
        }

        private void sendMsgForAll(String message) {

            LOGGER.info(message);

            synchronized (connections) {
                for (Connection connection : connections) {
                    connection.out.println(initMessageOut(message, server));
                }
            }
        }

        public void close() {
            try {
                socket.close();
                in.close();
                out.close();

                connections.remove(this);
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("Error closing connections!");
            }
        }
    }
}
