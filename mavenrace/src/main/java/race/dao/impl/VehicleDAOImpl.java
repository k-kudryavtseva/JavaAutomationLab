package race.dao.impl;

import org.apache.ibatis.session.SqlSession;
import race.config.SessionFactory;
import race.vehicle.Vehicle;

public class VehicleDAOImpl {
    private final static String namespace = "vehicle_mapper";

    public static void createVehicle(Vehicle vehicle) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.insert(namespace + ".createVehicle", vehicle);
        sqlSession.commit();
        sqlSession.close();
    }

}
