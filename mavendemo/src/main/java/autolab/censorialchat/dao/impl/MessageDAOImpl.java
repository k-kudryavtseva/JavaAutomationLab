package autolab.censorialchat.dao.impl;

import autolab.censorialchat.classes.mainclasses.Message;
import autolab.censorialchat.config.SessionFactory;
import autolab.censorialchat.dao.MessageDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    private final static String namespace = "message_mapper";

    @Override
    public void create(Message message) {
        System.out.println('1');
        SqlSession sqlSession = SessionFactory.getSession();
        System.out.println('4');
        sqlSession.insert(namespace + ".create", message);
        System.out.println('5');
        sqlSession.commit();
        System.out.println('6');
        sqlSession.close();
        System.out.println('7');
    }

    @Override
    public Message getMessageByID(int id) {
        System.out.println("3333");
        SqlSession sqlSession = SessionFactory.getSession();

        HashMap<String, Integer> m = new HashMap<>();
        m.put("id", id);

        Message a = sqlSession.selectOne(namespace + ".getMessageByID", m);
        System.out.println("3334");
        sqlSession.close();

        return a;
    }

    @Override
    public List<Message> getMessageFromDate(Date date) {
        SqlSession sqlSession = SessionFactory.getSession();
        List<Message> as = sqlSession.selectList(namespace + ".getFromDate", date);
        sqlSession.close();
        return as;
    }

    @Override
    public List<Message> getMessage() {
        SqlSession sqlSession = SessionFactory.getSession();
        List<Message> as = sqlSession.selectList(namespace + ".get");
        System.out.println(as);
        sqlSession.close();
        return as;
    }

    @Override
    public int getLastMessageID() {
        SqlSession sqlSession = SessionFactory.getSession();
        int lastMessageID = sqlSession.selectOne(namespace + ".getLastMessageID");
        sqlSession.close();

        return lastMessageID;
    }

    @Override
    public void update(Message message) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.update(namespace + ".update", message);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.delete(namespace + ".deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }
}
