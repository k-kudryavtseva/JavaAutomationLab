package race.vehicle;

import race.engine.Engine;
import race.wheel.Wheel;

public class Truck extends Vehicle {
    public Truck(String name, Float mass, Engine engine, Wheel wheel) {
        super(
                name,
                mass,
                engine,
                wheel
        );
    }
}
