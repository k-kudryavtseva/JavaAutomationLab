package formatter.dao.impl;

import formatter.config.SessionFactory;
import formatter.dao.UserDAO;
import formatter.user.User;
import org.apache.ibatis.session.SqlSession;

public class UserDAOImpl implements UserDAO {
    private final static String namespace = "user_mapper";

   @Override
   public void createUser(User user) {
       SqlSession sqlSession = SessionFactory.getSession();
       sqlSession.insert(namespace + ".createUser", user);
       sqlSession.commit();
       sqlSession.close();
   }

   @Override
   public User checkLogin(String login) {
       SqlSession sqlSession = SessionFactory.getSession();
       User having = sqlSession.selectOne(namespace + ".checkLogin", login);
       System.out.println(having);
       sqlSession.close();
       return having;
   }

   @Override
    public User getSaltByLogin(String login) {
       SqlSession sqlSession = SessionFactory.getSession();
       User salt = sqlSession.selectOne(namespace + ".getSaltByLogin", login);
       System.out.println(salt);
       sqlSession.close();
       return salt;
   }

   @Override
    public User getHashBySalt(String salt) {
       SqlSession sqlSession = SessionFactory.getSession();
       User user = sqlSession.selectOne(namespace + ".getHashBySalt", salt);
       System.out.println(user);
       sqlSession.close();
       return user;
   }
}
