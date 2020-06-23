package race.dao;

import race.engine.Engine;

import java.util.List;

public interface EngineDAO {
    List<Engine> getEngine(); // список двигателей
    List<Integer> getCylinders(); // список кол-ва цилиндров
    float getEngPByCylinders(int cylinders); // мощность по кол-ву цилиндров
}
