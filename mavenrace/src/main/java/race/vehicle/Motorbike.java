package race.vehicle;

import race.engine.Engine;
import race.wheel.Wheel;

public class Motorbike extends Vehicle {
    public Motorbike(String name, Float mass, Engine engine, Wheel wheel) {
        super(
                name,
                mass,
                engine,
                wheel
        );
    }
}
