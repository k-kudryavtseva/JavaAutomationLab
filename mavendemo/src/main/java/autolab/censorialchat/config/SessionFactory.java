package autolab.censorialchat.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.*;

public class SessionFactory {

    public static final Logger LOGGER = Logger.getLogger(SessionFactory.class);

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            System.out.println('6');
            String resource = "mybatisconfig.xml";
            InputStream is  = Resources.getResourceAsStream(resource);
            System.out.println(is);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            System.out.println('8');
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
}
