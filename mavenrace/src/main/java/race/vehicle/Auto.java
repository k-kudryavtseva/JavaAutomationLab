package race.vehicle;

import race.engine.Engine;
import race.wheel.Wheel;

public class Auto extends Vehicle {
    public Auto(String name, Float mass, Engine engine, Wheel wheel) {
        super(
                name,
                mass,
                engine,
                wheel
        );
    }
}
