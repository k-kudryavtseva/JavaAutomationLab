package formatter.dao;

import formatter.user.User;

import java.util.List;

public interface UserDAO {
    void createUser(User user);
    User checkLogin(String login);
    User getSaltByLogin(String login);
    User getHashBySalt(String salt);

//    static void createLogin(String login) {
//        SqlSession sqlSession = SessionFactory.getSession();
//        sqlSession.insert(namespace + ".createLogin", login);
//        sqlSession.commit();
//        sqlSession.close();
//    };
//
//
//    static void createPassword(String password) {
//        SqlSession sqlSession = SessionFactory.getSession();
//        sqlSession.insert(namespace + ".createPassword", password);
//        sqlSession.commit();
//        sqlSession.close();
//    };
}
