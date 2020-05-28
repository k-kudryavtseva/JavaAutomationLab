package autolab.censorialchat.classes.c10;

import com.vdurmont.emoji.EmojiParser;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.regex.Pattern;


public class Server {
    private final static Logger LOGGER = Logger.getLogger(Server.class);

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8001;

    private static final String PATH_TO_BAD_WORDS = System.getProperty("user.dir") + "/src/main/java/autolab/censorialchat/swearwords.txt";

    private HashSet<String> taboo;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new Server();
    }

    private final List<Connection> connections = Collections.synchronizedList(new ArrayList<>());
    private final List<String> chatHistory = Collections.synchronizedList(new ArrayList<>());
    private ServerSocket server;

    private void readBadWords() {
        taboo = new HashSet<String>();
        try (FileReader reader = new FileReader(PATH_TO_BAD_WORDS);
             BufferedReader bufferedReader = new BufferedReader(reader))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                taboo.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() {
        try {
            server = new ServerSocket(PORT);
            LOGGER.info("server up");

            readBadWords();

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
                    processedMessage = validateMessage(processedMessage);
                    processedMessage = EmojiParser.parseToUnicode(processedMessage);

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

    private static String replaceSymbol(String badword) {
        Random random = new Random();
        int index = random.nextInt(badword.length());
        return badword.replaceFirst(badword.substring(index, index + 1), "(•_•)");
    }

    private String validateMessage(String message) {
        if (Pattern.matches(".*\\p{InCyrillic}.*", message)) {
            return "Cyrillic message which we can't show";
        } else {
            String modifiedMessage = message;
            for (String badword : taboo) {
                if (modifiedMessage.contains(badword)) {
                    modifiedMessage = modifiedMessage.replace(badword, replaceSymbol(badword));
                }
            }
            return modifiedMessage;
        }
    }
}
