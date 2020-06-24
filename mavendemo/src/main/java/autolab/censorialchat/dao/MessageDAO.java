package autolab.censorialchat.dao;

import autolab.censorialchat.classes.mainclasses.Message;

import java.util.Date;
import java.util.List;

public interface MessageDAO {
    void create(Message message);
    Message getMessageByID(int id); // возвращает одно сообщение по id
    List<Message> getMessage(); // возвращает список сообщений
    List<Message> getMessageFromDate(Date date); // возвращает список сообщений по дате
    int getLastMessageID();
    void update(Message message);
    void delete(int id);
}
