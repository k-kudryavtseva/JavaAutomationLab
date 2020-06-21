package autolab.censorialchat.service;

import autolab.censorialchat.classes.mainclasses.Message;
import autolab.censorialchat.dao.MessageDAO;
import autolab.censorialchat.dao.impl.MessageDAOImpl;

import java.util.List;

public class MessageService {
    MessageDAO authorDAO = new MessageDAOImpl();

    public Message getAuthorById(long id) {
        return authorDAO.getById(id);
    }

    public List<Message> getAllAuthors() {
        return authorDAO.get();
    }

    public void createAuthor(Message author) {
        authorDAO.create(author);
    }

    public void deleteAuthorById(long id) {
        authorDAO.delete(id);
    }

    public void updateAuthor(Message author) {
        authorDAO.update(author);
    }
}
