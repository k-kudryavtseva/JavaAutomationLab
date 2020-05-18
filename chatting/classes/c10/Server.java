package JavaAutomationLab.chatting.classes.c10;

import JavaAutomationLab.chatting.classes.bo.ConnectMessage;
import JavaAutomationLab.chatting.classes.bo.ResponseMessage;
import JavaAutomationLab.chatting.io.interfaces.Packable;
import JavaAutomationLab.chatting.constant.TimeConstant;
import JavaAutomationLab.chatting.util.SerializationUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getSimpleName());

    private static final List<String> AVAILABLE_CLIENTS = new ArrayList<>();
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;


    public static void main(String[] args) {
        LOGGER.info(String.format("Listening on %s:%d", HOST, PORT));

        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader is = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            try {

                assert serverSocket != null;
                socket = serverSocket.accept();
                LOGGER.info("Successfully connected to " + socket);
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                AVAILABLE_CLIENTS.add(is.readLine());

                for (String clientPath : AVAILABLE_CLIENTS) {
                    listen(clientPath);
                }


                //listen();
                Thread.sleep(TimeConstant.TIME_TO_DELAY);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: filter msgs
    static void listen(String path) {
        SerializationUtil su = new SerializationUtil(path, path);
        Packable obj = su.readObject();
        if (obj != null) {
            ConnectMessage msg = ((ConnectMessage) obj);
            if (msg.getHost().equals(HOST) && msg.getPort() == PORT && AVAILABLE_CLIENTS.contains(msg.getToken())) {
                LOGGER.info(msg.getMessage());
                Packable resp = new ResponseMessage(HOST, PORT, "", "SUCCESS", 200);
                sendResponse(path, resp);
            }

        }
    }

    private static void sendResponse(String path, Packable pkg) {
        new SerializationUtil(path, path).writeResponse(pkg);
    }
}


class Connection {
    BufferedReader is;
    Socket socket;
    Server server;

    public Connection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;

        try {
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException {
        String input;

        while (true) {
            input = is.readLine();

            //server.listen();

            if (input.equalsIgnoreCase("quit")) {
                is.close();
                socket.close();
                break;
            }

        }
    }
}