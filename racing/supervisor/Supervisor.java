package JavaAutomationLab.racing.supervisor;

import JavaAutomationLab.racing.exception.UnknownRoadMaterialException;
import JavaAutomationLab.racing.race.Race;
import JavaAutomationLab.racing.route.Route;
import JavaAutomationLab.racing.route.RouteSection;
import JavaAutomationLab.racing.vehicle.Vehicle;

import java.util.*;

public class Supervisor {
    private int currentTick;
    private HashMap<String, Float> vehicleTicksMap = new HashMap<>();

    public Supervisor() {
    }

    public float getCurrentTicks() {
        return currentTick;
    }

    /**
     * Adds ticks spent during current step to previously spent ticks
     * @param ticks - quantity of passed ticks, float
     */
    public void updateCurrentTicks(float ticks) {
        this.currentTick += ticks;
    }

    /**
     * Creates map of exclusive vehicles
     * @param vehicles - vehicles participating in the race
     * @throws RuntimeException - if a vehicle isn't exclusive
     */
    public void createVehiclesMap(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle: vehicles) {
            if (vehicleTicksMap.containsKey(vehicle.getName())) {
                throw new RuntimeException("All Vehicles must have unique names!");
            }
            else {
                vehicleTicksMap.put(vehicle.getName(), 0f);
            }
        }
    }

    public void startRacing(ArrayList<Vehicle> vehicles) {
        createVehiclesMap(vehicles);
        System.out.println("Start!");
    }

    public void endRacing() {
        System.out.println("Race is over!");
        announceWinner();
    }

    public void announceWinner() {
        System.out.println("Final Standings");
        printSortedVehiclesByTime();
    }

    /**
     * Prints list of vehicles according to the spent time on the race (from the smallest to the largest time),
     * where spent time = quantity of ticks
     */
    public void printSortedVehiclesByTime() {

        Comparator<Map.Entry<String, Float>> comp = Comparator.comparing(obj -> obj.getValue());
        Set<Map.Entry<String, Float>> entries = vehicleTicksMap.entrySet();
        // Sort method needs a List, so let's first convert Set to List in Java
        List<Map.Entry<String, Float>> listOfEntries = new ArrayList<Map.Entry<String, Float>>(entries);
        listOfEntries.sort(comp);
        for(Map.Entry<String, Float> entry : listOfEntries){
            System.out.println(entry.getKey() + " ==> " + entry.getValue());
        }
    }

    /**
     * Moves vehicles along the route (until all vehicles finish or current tick is spent)
     * @param race - an object of class Race
     * @see Race # consists of route sections
     */
    public void nextTick(Race race) throws UnknownRoadMaterialException {

        boolean isFinished = true;

        Route route = race.getRoute();

        List<Vehicle> vehicles = race.getVehicles();

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle currentVehicle = vehicles.get(i);

            float point = currentVehicle.getPoint();

            if (point >= route.getTotalDistance()) {
                continue;
            }

            float currentRouteSectionIndex = (int) point;
            RouteSection currentRouteSection = route.getRouteSections().get((int) currentRouteSectionIndex);

            float tickCapacity = 1F;
            float ticksSpent = 0F;

            while (tickCapacity - ticksSpent > 0F) {

                RouteSection currentVector = route.getRouteSections().get((int) currentRouteSectionIndex);

                float currentSpeed = currentVehicle.getSpeedCurrent(currentVector.getMaterial());

                if (point + currentSpeed * (tickCapacity - ticksSpent) > currentRouteSectionIndex + 1) {

                    currentRouteSectionIndex += 1.0f; // переходим на следующий участок
                    ticksSpent = ((float) currentRouteSectionIndex - point) / currentSpeed;
                    point = (float) currentRouteSectionIndex;

                } else {

                    point += currentSpeed * (tickCapacity - ticksSpent);
                    ticksSpent = tickCapacity;
                }

                if (point < route.getTotalDistance()) {
                    isFinished = false;
                } else {
                    tickCapacity = ticksSpent;
                    System.out.println(currentVehicle.getName() + " has just finished!");
                }

                currentVehicle.setPoint(point);
                float prevTicks = vehicleTicksMap.get(currentVehicle.getName());
                vehicleTicksMap.put(currentVehicle.getName(), prevTicks + ticksSpent);
            }
        }

        if (isFinished) {
            race.setFinished(true);
            endRacing();
        }
    }
}
