package autolab.censorialchat.classes.c10;

import autolab.censorialchat.classes.bo.ConnectMessage;
import autolab.censorialchat.classes.bo.ResponseMessage;
import autolab.censorialchat.constant.TimeConstant;
import autolab.censorialchat.io.interfaces.Packable;
import autolab.censorialchat.util.SerializationUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getSimpleName());

    private static final List<String> AVAILABLE_CLIENTS = Arrays.asList("user");
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;


    public static void main(String[] args) throws IOException {

        // Set up a simple configuration that logs on the console.
        BasicConfigurator.configure();

        LOGGER.info(String.format("Listening on %s:%d", HOST, PORT));

        //array list, set = read file

        while (true) {
            HashSet<String> taboo = new HashSet<String>();
            try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/autolab/censorialchat/swearwords.txt");
                 BufferedReader bufferedReader = new BufferedReader(reader))
            {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    taboo.add(line.toLowerCase());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                listen(taboo);
                Thread.sleep(TimeConstant.TIME_TO_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: filter msgs
    private static void listen(HashSet<String> swearwords) {
        Packable obj = (Packable) SerializationUtil.readObject();
        if (obj != null) {
            ConnectMessage message = ((ConnectMessage) obj);
            if (message.getHost().equals(HOST) && message.getPort() == PORT && AVAILABLE_CLIENTS.contains(message.getToken())) {
                Packable resp = new ResponseMessage(HOST, PORT, "", "SUCCESS", 200);
                sendResponse(resp);
                if (Pattern.matches(".*\\p{InCyrillic}.*", message.getMessage())) {
                    //resp = new ResponseMessage(HOST, PORT, "", "Please use only latin symbols", 200);
                    //sendResponse(resp);
                    LOGGER.info("Cyrillic message which we can't show");
                } else {
                    String modifiedMessage = message.getMessage();
                    for (String badword : swearwords) {
                        if (modifiedMessage.contains(badword)) {
                            modifiedMessage = modifiedMessage.replace(badword, replaceSymbol(badword));
                        }
                    }
                    LOGGER.info(modifiedMessage);
                    SerializationUtil.zeroizeFile("src/main/resources/serial");
                    //resp = new ResponseMessage(HOST, PORT, "", modifiedMessage, 200);
                    //sendResponse(resp);
                }
            }
        }
    }

    private static String replaceSymbol(String badword) {
        Random random = new Random();
        int index = random.nextInt(badword.length());
        return badword.replaceFirst(badword.substring(index, index+1), "(•_•)");
    }

    private static void sendResponse(Packable pkg) {
        SerializationUtil.writeResponse(pkg);
    }
}
