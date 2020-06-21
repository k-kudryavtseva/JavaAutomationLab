package autolab.censorialchat.dao.impl;

import autolab.censorialchat.classes.mainclasses.Message;
import autolab.censorialchat.config.SessionFactory;
import autolab.censorialchat.dao.MessageDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    private final static String namespace = "message_mapper";

    @Override
    public void create(Message message) {
        System.out.println('5');
        SqlSession sqlSession = SessionFactory.getSession();
        System.out.println('3');
        sqlSession.insert(namespace + ".create", message);
        System.out.println('4');
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Message getById(long id) {
        SqlSession sqlSession = SessionFactory.getSession();
        Message a = sqlSession.selectOne(namespace + ".getById", id);
        sqlSession.close();
        return a;
    }

    @Override
    public List<Message> getFromDate(Date date) {
        SqlSession sqlSession = SessionFactory.getSession();
        List<Message> as = sqlSession.selectList(namespace + ".getFromDate", date);
        sqlSession.close();
        return as;
    }

    @Override
    public List<Message> get() {
        SqlSession sqlSession = SessionFactory.getSession();
        List<Message> as = sqlSession.selectList(namespace + ".get");
        System.out.println(as);
        sqlSession.close();
        return as;
    }

    @Override
    public void update(Message message) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.update(namespace + ".update", message);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delete(long id) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.delete(namespace + ".deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }
}
