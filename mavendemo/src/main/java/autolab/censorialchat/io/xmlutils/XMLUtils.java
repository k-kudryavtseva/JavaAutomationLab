package autolab.censorialchat.io.xmlutils;


import autolab.censorialchat.classes.c10.Client;
import autolab.censorialchat.classes.c10.Message;
import autolab.censorialchat.classes.c10.MultithreadedServer;
import autolab.censorialchat.classes.c10.Server;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

public class XMLUtils {

    public static String readXml(BufferedReader in) throws IOException {
        StringBuilder xml = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            if(line.isEmpty()) break;
            xml.append(line);
        }
        return xml.toString();
    }

    public static Message getMessageIn(String msg, XMLUnmarshaller xmlUnmarshaller) throws JAXBException {
        return (Message) xmlUnmarshaller.getUnmarshalledXml(msg);
    }

    public static String initMessageOut(String msg, MultithreadedServer multiThreadedServer){
        return initMessageOut(msg, multiThreadedServer.getServer());
    }

    public static String initMessageOut(String msg, Server server){
        Message messageOut = new Message(
                Server.getHOST(),
                Server.getPORT(),
                Server.getTOKEN(),
                msg,
                new Date()
        );

        try {
            return Server.getXmlMarshaller().getXml(messageOut);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return "ERROR";
    }

    public static String initMessageOut(String msg, Client client){

        Message messageOut = new Message(
                client.getHOST(),
                client.getPORT(),
                client.getTOKEN(),
                msg,
                new Date()
        );

        try {
            return Client.getXmlMarshaller().getXml(messageOut);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return "ERROR";
    }
}
