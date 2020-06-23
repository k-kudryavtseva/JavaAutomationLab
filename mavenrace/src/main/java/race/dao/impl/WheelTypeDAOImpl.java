package race.dao.impl;

import org.apache.ibatis.session.SqlSession;
import race.config.SessionFactory;
import race.dao.WheelTypeDAO;
import race.engine.Engine;
import race.wheel.Wheel;

import java.util.List;

public class WheelTypeDAOImpl implements WheelTypeDAO {
    private final static String namespace = "wheeltype_mapper";

    @Override
    public List<Wheel> getWheelType() {
        SqlSession sqlSession = SessionFactory.getSession();
        List<Wheel> wheels = sqlSession.selectList(namespace + ".getWheelType");
        System.out.println(wheels);
        sqlSession.close();
        return wheels;
    }
}
