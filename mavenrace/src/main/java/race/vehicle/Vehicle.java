package race.vehicle;

import race.engine.Engine;
import race.exception.UnknownRoadMaterialException;
import race.wheel.Wheel;

public class Vehicle {
    private String name;
    private Engine engine;
    private Wheel wheel;
    private float mass; // in tones
    private float point;

    public Vehicle(
            String name,
            float mass,
            Engine engine,
            Wheel wheel
    ) {
        this.name = name;
        this.mass = mass;
        this.engine = engine;
        this.wheel = wheel;
    }

    public String getName() {
        return name;
    }

    public float getPoint() {
        return point;
    }

    public float getSpeedCurrent(String materialName) throws UnknownRoadMaterialException {

        float frictionCoef = wheel.getFrictionCoef(materialName);
        float powerPerKg = engine.getEnginePower() / mass;

        return powerPerKg * frictionCoef;

    }

    public void setPoint(float point) {
        this.point = point;
    }

    public Engine getEngine() {
        return engine;
    }
    public Wheel getWheel() { return wheel; }
    public float getMass() {
        return mass;
    }
}
