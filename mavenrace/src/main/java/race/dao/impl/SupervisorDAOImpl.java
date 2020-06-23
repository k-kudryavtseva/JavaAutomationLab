package race.dao.impl;

import org.apache.ibatis.session.SqlSession;
import race.config.SessionFactory;
import race.dao.SupervisorDAO;
import race.supervisor.Supervisor;

public class SupervisorDAOImpl implements SupervisorDAO {
    private final static String namespace = "supervisor_mapper.xml";

    @Override
    public void createSupervisor(Supervisor supervisor) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.insert(namespace + ".createSupervisor", supervisor);
        sqlSession.commit();
        sqlSession.close();
    }
}
