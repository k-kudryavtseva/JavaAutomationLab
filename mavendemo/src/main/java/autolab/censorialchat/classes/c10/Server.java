package autolab.censorialchat.classes.c10;

import autolab.censorialchat.filters.*;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Server {
    private final static Logger LOGGER = Logger.getLogger(Server.class);
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 8001;
    private NERPersonFilter nerPersonFilter;
    private NERLocationFilter nerLocationFilter;
    private CensorialFilter censorialFilter;
    private EmojiFilter emojiFilter;
    private SpaceFilter spaceFilter;
    private ArrayList<IFilter> filtersList;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new Server();
    }

    private final List<Connection> connections = Collections.synchronizedList(new ArrayList<>());
    private final List<String> chatHistory = Collections.synchronizedList(new ArrayList<>());
    private ServerSocket server;

    public Server() {
        try {

            nerPersonFilter = new NERPersonFilter();
            nerLocationFilter = new NERLocationFilter();
            censorialFilter = new CensorialFilter();
            emojiFilter  = new EmojiFilter();
            spaceFilter = new SpaceFilter();
            filtersList = new ArrayList<>();
            filtersList.add(censorialFilter);
            filtersList.add(emojiFilter);
            filtersList.add(nerPersonFilter);
            filtersList.add(nerLocationFilter);
            filtersList.add(spaceFilter);

            server = new ServerSocket(PORT);
            LOGGER.info("server up");

            CensorialFilter.readBadWords();

            while (true) {
                Socket socket = server.accept();

                Connection connection = new Connection(socket);
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

        private String name = "";

        public Connection(Socket socket) {
            this.socket = socket;

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

                name = in.readLine();
                sendMsgForAll(name + " connected");

                sendChatHistory();

                String str = "";
                while (true) {
                    str = in.readLine();
                    if (str.equalsIgnoreCase("quit")) break;

                    String processedMessage = str;

                    for (IFilter filterObj : filtersList) {
                        processedMessage = filterObj.filter(processedMessage);
                    }

                    processedMessage = name + ": " + processedMessage;
                    sendMsgForAll(processedMessage);
                    chatHistory.add(processedMessage);
                }

                sendMsgForAll(name + ": " + "disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
        
        private void sendChatHistory() {
            //chatHistory.forEach(historyMsg -> out.println(historyMsg));
            for (String str: chatHistory) {
                out.println(str);
            }
        }

        private void sendMsgForAll(String message) {

            LOGGER.info(message);

            synchronized (connections) {
                for (Connection connection : connections) {
                    connection.out.println(message);
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
