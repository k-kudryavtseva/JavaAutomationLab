package formatter.dao.impl;

import formatter.config.SessionFactory;
import formatter.dao.UserDAO;
import formatter.user.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
   public List getAllLogins() {
       SqlSession sqlSession = SessionFactory.getSession();
       List<User> allLogins = sqlSession.selectList(namespace + ".getAllLogins");
       System.out.println(allLogins);
       sqlSession.close();
       return allLogins;
   }
}
