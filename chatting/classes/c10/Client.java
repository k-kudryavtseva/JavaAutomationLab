package JavaAutomationLab.chatting.classes.c10;

import JavaAutomationLab.chatting.classes.bo.ConnectMessage;
import JavaAutomationLab.chatting.classes.bo.ResponseMessage;
import JavaAutomationLab.chatting.io.interfaces.Packable;
import JavaAutomationLab.chatting.constant.C10Constant;
import JavaAutomationLab.chatting.util.PropertyUtil;
import JavaAutomationLab.chatting.util.SerializationUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;

/**
 * 1. object streams
 * 2. task
 * 3. swap strings
 * 33. loggers (stdin, stdout, stderr)
 * 4. refactoring
 * 5. fixes
 */
public class Client {

    public static void main(String[] args) throws IOException {
        final String HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
        final int PORT = Integer.valueOf(PropertyUtil.getValueByKey(C10Constant.PORT));
        final String TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);

        Socket socket = null;
        DataOutputStream os = null;

        String uid = UUID.randomUUID().toString().replace("-", "");

        String path = System.getProperty("user.dir") + "\\src\\JavaAutomationLab\\client_" + uid;
        String responsePath = path + "_response";
        Path p1 = Paths.get(path);
        Path p2 = Paths.get(responsePath);

        try {
            Files.createFile(p1);
            Files.createFile(p2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket = new Socket(HOST, PORT);
            os = new DataOutputStream(socket.getOutputStream());
            System.out.println(path);
            os.writeBytes(path + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        connect(path, HOST, PORT, TOKEN);
        //String msg = ((ResponseMessage) getResponse(responsePath)).getResp();
        //if (msg != null) {
        //    System.out.println(msg);
        //}

        Scanner in = new Scanner(System.in);

        while (true) {
            String answer = in.nextLine();

            if (answer.equalsIgnoreCase("quit")) {
                System.out.println("Bye bye");
                break;
            }

            Packable pkg = new ConnectMessage(HOST, PORT, TOKEN, answer);
            new SerializationUtil(path, path + "_response").writeObject(pkg);
        }

    }

    private static void connect(String path, final String host, final int port, final String token) {
        String msg = "Conn";
        Packable pkg = new ConnectMessage(host, port, token, msg);
        new SerializationUtil(path, path).writeObject(pkg);
    }

    private static Packable getResponse(String path) {
        return new SerializationUtil(path, path).readResponse();
    }
}
