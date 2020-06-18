package autolab.censorialchat.io.xmlutils;


import autolab.censorialchat.classes.mainclasses.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class XMLUtil {

    public static void writeMessage(String pathTo, Message msg) throws JAXBException, IOException {
        File msgFile = new File(pathTo).getAbsoluteFile();
        msgFile.createNewFile();
        JAXBContext context = JAXBContext.newInstance(Message.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(msg, msgFile);
    }

    public static Message readMessage(String pathTo) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Message.class);
        return (Message) context.createUnmarshaller()
                .unmarshal(new FileReader(pathTo));
    }

//    public static String readXml(BufferedReader in) throws IOException {
//        StringBuilder xml = new StringBuilder();
//        String line;
//        while ((line = in.readLine()) != null) {
//            if(line.isEmpty()) break;
//            xml.append(line);
//        }
//        return xml.toString();
//    }
//
//    public static Message getMessageIn(String msg, XMLUnmarshaller xmlUnmarshaller) throws JAXBException {
//        return (Message) xmlUnmarshaller.getUnmarshalledXml(msg);
//    }
//
//    public static String initMessageOut(String msg, MultithreadedServer multiThreadedServer){
//        return initMessageOut(msg, multiThreadedServer.getServer());
//    }
//
//    public static String initMessageOut(String msg, Server server){
//        Message messageOut = new Message(
//                Server.getHOST(),
//                Server.getPORT(),
//                Server.getTOKEN(),
//                msg,
//                new Date()
//        );
//
//        try {
//            return Server.getXmlMarshaller().getXml(messageOut);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        return "ERROR";
//    }
//
//    public static String initMessageOut(String msg, Client client){
//
//        Message messageOut = new Message(
//                client.getHOST(),
//                client.getPORT(),
//                client.getTOKEN(),
//                msg,
//                new Date()
//        );
//
//        try {
//            return Client.getXmlMarshaller().getXml(messageOut);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        return "ERROR";
//    }
}
