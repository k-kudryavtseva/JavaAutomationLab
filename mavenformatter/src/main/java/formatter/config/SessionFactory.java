package formatter.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class SessionFactory {

    public static final Logger LOGGER = Logger.getLogger(SessionFactory.class);

    private static SqlSessionFactory sqlSessionFactory = null; // SQLSessionFactory - базовый класс для каждого приложения MyBatis

    static {
        try {
            String resource = "mybatisconfig.xml";
            InputStream inputStream  = Resources.getResourceAsStream(resource); // Читаем файл с настройками подключения и настройками MyBatis
            System.out.println(inputStream);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
}
