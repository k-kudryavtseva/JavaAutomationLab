package autolab.censorialchat.classes.mainclasses;

import autolab.censorialchat.dao.impl.MessageDAOImpl;
import autolab.censorialchat.filters.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class Server {
    private final static Logger LOGGER = Logger.getLogger(Server.class);
    private static ArrayList<IFilter> filterList;
    public static MessageDAOImpl messageDAOImpl = new MessageDAOImpl();

    public static void main(String[] args) throws InterruptedException {
        BasicConfigurator.configure();
        initFilters();
        listen();
    }

    private static void initFilters() {
        try {
            filterList = new ArrayList<>();
            filterList.add(new EmojiFilter());
            filterList.add(new NERPersonFilter());
            filterList.add(new NERLocationFilter());
            filterList.add(new CensorialFilter());
            filterList.add(new SpaceFilter());
            filterList.add(new CapitalizeFilter());

            CensorialFilter.readBadWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() { }

    static void listen() throws InterruptedException {
        LOGGER.info("server up");
        while (true) {
            Message msg = messageDAOImpl.getMessageByID(messageDAOImpl.getLastMessageID());
            String processedMessage = msg.getMsg();
            if (processedMessage.equalsIgnoreCase("quit")) {
                processedMessage = "Somebody quitted";
            } else {
                for (IFilter filterObj : filterList) {
                    processedMessage = filterObj.filter(processedMessage);
                }
            }

            LOGGER.info(msg.getDate().toString() + ": " + processedMessage);
            msg.setProcessed_msg(processedMessage);
            messageDAOImpl.update(msg);
            Thread.sleep(10000);
        }
    }
}
