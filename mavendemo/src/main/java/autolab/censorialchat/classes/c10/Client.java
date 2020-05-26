package autolab.censorialchat.classes.c10;

import autolab.censorialchat.constant.C10Constant;
import autolab.censorialchat.util.PropertyUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;


public class Client {
    private final static Logger LOGGER = Logger.getLogger(Client.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new Client();
    }

    private final String HOST;
    private final int PORT;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final Scanner scanner;

    public Client(){
        this.scanner = new Scanner(System.in);

        HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
        PORT = Integer.parseInt(PropertyUtil.getValueByKey(C10Constant.PORT));

        UUID uuid = UUID.randomUUID();

        try {
            try {
                initClient();

                out.println(uuid.toString());

                Listener listener = new Listener();
                listener.start();

                String str = "";
                while (!str.equalsIgnoreCase("quit")) {
                    str = scanner.nextLine();
                    out.println(str);
                }

            } finally {
                close();
            }
        } catch (IOException e){
            LOGGER.error("Something went wrong. Reload chat");
            e.printStackTrace();
        }
    }

    private void initClient() throws IOException {
        this.socket = new Socket(this.HOST, this.PORT);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        LOGGER.info(String.format("Connected to %s:%s", this.HOST, this.PORT));
    }

    private void close(){
        try {
            if (socket != null){
                socket.close();
            }
            in.close();
            out.close();
            System.exit(-1);
        } catch (IOException e) {
            LOGGER.error("Error closing connections");
            e.printStackTrace();
        }
    }

    private class Listener extends Thread {
        private boolean stop;

        public void setStop() {
            stop = true;
        }

        @Override
        public void run() {
            try {
                while (!stop) {
                    String str = in.readLine();
                    LOGGER.info(str);
                }
            } catch (IOException e) {
                setStop();
                e.printStackTrace();
            }
        }
    }
}
