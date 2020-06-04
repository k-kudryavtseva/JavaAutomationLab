package autolab.censorialchat.classes.c10;

import autolab.censorialchat.constant.C10Constant;
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
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

import static autolab.censorialchat.io.xmlutils.XMLUtils.*;


public class Client implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(Client.class);

    private static XMLMarshaller xmlMarshaller;
    private static XMLUnmarshaller xmlUnmarshaller;

    private UUID uuid;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new Client();
    }

    private final String HOST;
    private final int PORT;
    private final String TOKEN;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final Scanner scanner;

    public int getPORT() {
        return PORT;
    }

    public String getHOST() {
        return HOST;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public static XMLMarshaller getXmlMarshaller() {
        return xmlMarshaller;
    }

    public Client() {
        this.scanner = new Scanner(System.in);

        HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
        PORT = Integer.parseInt(PropertyUtil.getValueByKey(C10Constant.PORT));
        TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);

        uuid = UUID.randomUUID();

        try {
            try {
                initClient();

                //out.println(uuid.toString());

                Listener listener = new Listener();
                listener.start();

                String str = "";
                while (!str.equalsIgnoreCase("quit")) {
                    str = scanner.nextLine();
                    //out.println(str);
                    out.println(initMessageOut(str, this));
                }

            } finally {
                close();
            }
        } catch (IOException | JAXBException e){
            LOGGER.error("Something went wrong. Reload chat");
            e.printStackTrace();
        }
    }

    private void initClient() throws IOException, JAXBException {
        this.socket = new Socket(this.HOST, this.PORT);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);

        JAXBContext context = JAXBContext.newInstance(Message.class);
        xmlMarshaller = new XMLMarshaller(context);
        xmlUnmarshaller = new XMLUnmarshaller(context);

        String msg = initMessageOut(uuid.toString(), this);
        out.println(msg);

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

    @Override
    public void run() {

        UUID uuid = UUID.randomUUID();

        try {

            initClient();

            //out.println(uuid.toString());

            Listener listener = new Listener();
            listener.start();

            String str = "";
            while (!str.equalsIgnoreCase("quit")) {
                str = scanner.nextLine();
                //out.println(str);
                out.println(initMessageOut(str, this));
            }

        } catch (IOException | JAXBException e) {
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

        @Override
        public void run() {
            try {
                while (!stop) {
                    //String str = in.readLine();
                    String str = getMessageIn(readXml(in), xmlUnmarshaller).getMsg();
                    LOGGER.info(str);
                }
            } catch (IOException | JAXBException e) {
                setStop();
                e.printStackTrace();
            }
        }
    }
}
