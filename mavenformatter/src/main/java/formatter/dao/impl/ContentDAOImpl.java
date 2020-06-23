package formatter.dao.impl;

import formatter.config.SessionFactory;
import org.apache.ibatis.session.SqlSession;

public class ContentDAOImpl {
    private final static String namespace = "mapper/content_mapper";

    public static void createContent(String inputText) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.insert(namespace + ".createContent", inputText);
        sqlSession.commit();
        sqlSession.close();
    }
}
