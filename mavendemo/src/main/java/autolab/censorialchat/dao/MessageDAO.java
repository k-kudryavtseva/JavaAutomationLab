package autolab.censorialchat.dao;

import autolab.censorialchat.classes.mainclasses.Message;

import java.util.Date;
import java.util.List;

public interface MessageDAO {
    void create(Message author);
    Message getById(long id);
    List<Message> get();
    List<Message> getFromDate(Date date);
    void update(Message message);
    void delete(long id);
}
