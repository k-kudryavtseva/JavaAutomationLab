package JavaAutomationLab.racing.vehicle;

import JavaAutomationLab.racing.engine.Engine;
import JavaAutomationLab.racing.wheel.Wheel;

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
