package autolab.censorialchat.service;

import autolab.censorialchat.classes.mainclasses.Message;
import autolab.censorialchat.dao.MessageDAO;
import autolab.censorialchat.dao.impl.MessageDAOImpl;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO = new MessageDAOImpl();

    public Message getMessageById(int id) {
        return messageDAO.getMessageByID(id);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getMessage();
    }

    public void createMessage(Message message) {
        messageDAO.create(message);
    }

    public void deleteMessageById(int id) {
        messageDAO.delete(id);
    }

    public void updateMessage(Message message) {
        messageDAO.update(message);
    }
}
