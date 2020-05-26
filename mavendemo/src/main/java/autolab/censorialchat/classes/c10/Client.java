package autolab.censorialchat.classes.c10;

import autolab.censorialchat.classes.bo.ConnectMessage;
import autolab.censorialchat.classes.bo.ResponseMessage;
import autolab.censorialchat.constant.C10Constant;
import autolab.censorialchat.io.interfaces.Packable;
import autolab.censorialchat.util.PropertyUtil;
import autolab.censorialchat.util.SerializationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 1. object streams
 * 2. task
 * 3. swap strings
 * 33. loggers (stdin, stdout, stderr)
 * 4. refactoring
 * 5. fixes
 */
public class Client extends Thread {

    public void run() {

    }

    public static void main(String[] args) throws IOException {
        final String HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
        final int PORT = Integer.parseInt(PropertyUtil.getValueByKey(C10Constant.PORT));
        final String TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);

        String path = System.getProperty("user.dir") + "/src/main/resources/client1";
        try {
            Path p = Paths.get(path);
            try {
                Files.createFile(p);
            } catch (IOException e) {
                e.printStackTrace();
            }

            connect(HOST, PORT, TOKEN);
            System.out.println(((ResponseMessage) getResponse()).getResp());

            Scanner in = new Scanner(System.in);

            while (true) {

                String answer = in.nextLine();

                if (answer.equalsIgnoreCase("quit")) {
                    System.out.println("Bye bye");
                    break;
                }

                Packable pkg = new ConnectMessage(HOST, PORT, TOKEN, answer);
                SerializationUtil.writeObject(pkg);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            Files.delete(Paths.get(path));
        }

    }

    private static void connect(final String host, final int port, final String token) {
        String msg = "Client connected";
        Packable pkg = (Packable) new ConnectMessage(host, port, token, msg);
        SerializationUtil.writeObject(pkg);
    }

    private static Packable getResponse() {
        return (Packable) SerializationUtil.readResponse();
    }
}
