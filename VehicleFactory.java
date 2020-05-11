package JavaAutomationLab;

import java.lang.reflect.InvocationTargetException;

public class VehicleFactory {
    /**
     * Creates vehicles using reflection
     * @param name
     * @param vehicleName
     * @param mass
     * @param engine
     * @param wheel
     * @return instance of vehicle inheritor
     */
    public static Vehicle createVehicle(String name, String vehicleName, float mass, Engine engine, Wheel wheel) {
        try {
            Class<?> vehicleClass = Class.forName(name);
            return (Vehicle) vehicleClass.getConstructor(String.class, Float.class, Engine.class, Wheel.class).newInstance(vehicleName, mass, engine, wheel);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}