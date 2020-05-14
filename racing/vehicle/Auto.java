package JavaAutomationLab.racing.vehicle;

import JavaAutomationLab.racing.engine.Engine;
import JavaAutomationLab.racing.wheel.Wheel;

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
