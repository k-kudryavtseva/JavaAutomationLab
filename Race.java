package JavaAutomationLab;

import java.util.ArrayList;

public class Race {
    private Route route;
    private ArrayList<Vehicle> vehicles;
    private boolean isFinished;
    private Supervisor supervisor;

    public Race(Route route, ArrayList<Vehicle> vehicles, Supervisor supervisor) {
        this.route = route;
        this.vehicles = vehicles;
        this.isFinished = false;
        this.supervisor = supervisor;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Route getRoute() {
        return route;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
