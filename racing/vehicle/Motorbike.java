package JavaAutomationLab.racing.vehicle;

import JavaAutomationLab.racing.engine.Engine;
import JavaAutomationLab.racing.wheel.Wheel;

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
