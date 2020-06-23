package race.dao.impl;

import org.apache.ibatis.session.SqlSession;
import race.config.SessionFactory;
import race.dao.EngineDAO;
import race.engine.Engine;

import java.util.List;

public class EngineDAOImpl implements EngineDAO {
    private final static String namespace = "engine_mapper";

    @Override
    public List<Engine> getEngine() {
        SqlSession sqlSession = SessionFactory.getSession();
        List<Engine> engines = sqlSession.selectList(namespace + ".getEngine");
        System.out.println(engines);
        sqlSession.close();
        return engines;
    }

    @Override
    public List<Integer> getCylinders() {
        SqlSession sqlSession = SessionFactory.getSession();
        List<Integer> cylinders = sqlSession.selectOne(namespace + ".getCylinders");
        System.out.println(cylinders);
        sqlSession.close();
        return cylinders;
    }

    @Override
    public float getEngPByCylinders(int cylinders) {
        SqlSession sqlSession = SessionFactory.getSession();
        float engPower = sqlSession.selectOne(namespace + ".getEPByCylinders", cylinders);
        System.out.println(engPower);
        sqlSession.close();
        return engPower;
    }
}
