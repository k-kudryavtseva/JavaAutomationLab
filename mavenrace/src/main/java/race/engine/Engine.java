package race.engine;

public class Engine {
    private String engineName;
    private int numberOfCylinders;
    private float enginePower;

    public Engine(int numberOfCylinders, float enginePower) {
        this.numberOfCylinders = numberOfCylinders;
        this.enginePower = enginePower;
    }

    public String getEngineName() {
        return engineName;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public float getEnginePower() {
        return enginePower;
    }
}
