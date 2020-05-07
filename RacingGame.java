package JavaAutomationLab;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class RacingGame {
    public static void main(String[] args) {

        Vehicle bugatti = VehicleFactory.createVehicle("JavaAutomationLab.Bugatti", "Bugatti Kristina", new Engine(0.7f));
        Vehicle kamaz = VehicleFactory.createVehicle("JavaAutomationLab.Kamaz", "Kamaz Schumacher", new Engine(0.3f));
        Vehicle suzuki = VehicleFactory.createVehicle("JavaAutomationLab.Suzuki", "Suzuki Mutsukhito", new Engine(0.5f));

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(bugatti);
        vehicles.add(kamaz);
        vehicles.add(suzuki);

        PointLocation pointStart = new PointLocation();
        PointLocation pointEnd = new PointLocation();
        RouteSection routeSection1 = new CityRoad(pointStart, pointEnd);
        RouteSection routeSection2 = new CountryRoad(pointStart, pointEnd);
        RouteSection routeSection3 = new HaulRoad(pointStart, pointEnd);
        RouteSection routeSection4 = new Highway(pointStart, pointEnd);

        List<RouteSection> routeSections = new ArrayList<>();
        routeSections.add(routeSection1);
        routeSections.add(routeSection2);
        routeSections.add(routeSection3);
        routeSections.add(routeSection4);

        Route route = new Route(routeSections);
        Race race = new Race(route, (ArrayList<Vehicle>) vehicles, new Supervisor());

        Scanner scanner = new Scanner(System.in);

        race.getSupervisor().startRacing(race.getVehicles());

        while (!race.getIsFinished()) {

            System.out.println("Press Enter to run next tick");
            scanner.nextLine();
            race.getSupervisor().nextTick(race);

        }
    }
}


class VehicleFactory {
    /**
     * Creates vehicles using reflection
     * @param name
     * @param vehicleName
     * @param engine
     * @return instance of vehicle inheritor
     */
    public static Vehicle createVehicle(String name, String vehicleName, Engine engine) {
        try {
            Class<?> vehicleClass = Class.forName(name);
            return (Vehicle) vehicleClass.getConstructor(String.class, Engine.class).newInstance(vehicleName, engine);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}

class Vehicle {
    private String name;
    private float point;
    private float speedCurrent;
    private Engine engine;

    public Vehicle(
            String name,
            Engine engine
            ) {
        this.name = name;
        this.engine = engine;
    }

    public String getName() {
        return name;
    }

    public float getPoint() {
        return point;
    }

    public float getSpeedCurrent() {
        return speedCurrent;
    }

    public void setSpeedCurrent(float speedCurrent) {
        this.speedCurrent = speedCurrent;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public Engine getEngine() {
        return engine;
    }
}

class Auto extends Vehicle {
    public Auto(String name, Engine engine) {
        super(name, engine);
    }
}

class Bugatti extends Auto {
    public Bugatti(String name, Engine engine) {
        super(
                name,
                engine
        );
    }
}

class Dodge extends Auto {
    public Dodge(String name, Engine engine) {
        super(
                name,
                engine
        );
    }
}

class Truck extends Vehicle {
    public Truck(String name, Engine engine) {
        super(
                name,
                engine
        );
    }
}

class Kamaz extends Truck {
    public Kamaz(String name, Engine engine) {
        super(
                name,
                engine
        );
    }
}

class Mercedes extends Truck {
    public Mercedes(String name, Engine enginee) {
        super(
                name,
                enginee
        );
    }
}

class Motorbike extends Vehicle {
    public Motorbike(String name, Engine engine) {
        super(
                name,
                engine
        );
    }
}

class Suzuki extends Motorbike {
    public Suzuki(String name, Engine engine) {
        super(
                name,
                engine
        );
    }
}

class Yamaha extends Motorbike {
    public Yamaha(String name, Engine engine) {
        super(
                name,
                engine
        );
    }
}

class Route {
    private List<RouteSection> routeSections;

    public Route(List<RouteSection> routeSections) {
        this.routeSections = routeSections;
    }

    public List<RouteSection> getRouteSections() {
        return routeSections;
    }

    public float getTotalDistance() {
        return routeSections.size();
    }
}

class PointLocation {
    private int x = 0;
    private int y = 0;
}

class RouteSection {
    private PointLocation pointStart = new PointLocation();
    private PointLocation pointEnd = new PointLocation();
    private String materialName;
    private float frictionCoef;

    public RouteSection(PointLocation pointStart, PointLocation pointEnd, String materialName, float frictionCoef) {
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
        this.materialName = materialName;
        this.frictionCoef = frictionCoef;
    }

    public String getMaterialName() {
        return materialName;
    }

    public float getFrictionCoef() {
        return frictionCoef;
    }
}

class Highway extends RouteSection {

    private static float frictionCoef = 0.9f;

    public Highway(PointLocation pointStart, PointLocation pointEnd) {
        super(
                pointStart,
                pointEnd,
                "Highway",
                0.9F
        );
    }
}

class CityRoad extends RouteSection {
    public CityRoad(PointLocation pointStart, PointLocation pointEnd) {
        super(
                pointStart,
                pointEnd,
                "CityRoad",
                0.7F
        );
    }
}

class CountryRoad extends RouteSection {
    public CountryRoad(PointLocation pointStart, PointLocation pointEnd) {
        super(
                pointStart,
                pointEnd,
                "CountryRoad",
                0.5F
        );
    }
}

class HaulRoad extends RouteSection {
    public HaulRoad(PointLocation pointStart, PointLocation pointEnd) {
        super(
                pointStart,
                pointEnd,
                "HaulRoad",
                0.3F
        );
    }
}

class Wheel {
    private float frictionCoef;

    public Wheel(float frictionCoef) {
        this.frictionCoef = frictionCoef;
    }

    public float getFrictionCoef() {
        return frictionCoef;
    }

    public void setFrictionCoef(float frictionCoef) {
        this.frictionCoef = frictionCoef;
    }
}

class AutoWheel extends Wheel {

    public AutoWheel(float frictionCoef) {
        super(frictionCoef);
    }
}

class TruckWheel extends Wheel {

    public TruckWheel(float frictionCoef) {
        super(frictionCoef);
    }
}

class MotorbikeWheel extends Wheel {

    public MotorbikeWheel(float frictionCoef) {
        super(frictionCoef);
    }
}

class Engine {
    private float speedMax;

    public Engine(float speedMax) {
        this.speedMax = speedMax;
    }

    public float getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(float speedMax) {
        this.speedMax = speedMax;
    }
}

class AutoEngine extends Engine {

    public AutoEngine(float speedMax) {
        super(speedMax);
    }
}

class TruckEngine extends Engine {

    public TruckEngine(float speedMax) {
        super(speedMax);
    }
}

class MotorbikeEngine extends Engine {

    public MotorbikeEngine(float speedMax) {
        super(speedMax);
    }
}

class Supervisor {
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

        Comparator<Map.Entry<String, Float>> valueComparator = new Comparator<Map.Entry<String,Float>>() {

            @Override
            public int compare(Map.Entry<String, Float> e1, Map.Entry<String, Float> e2) {
                float v1 = e1.getValue();
                float v2 = e2.getValue();
                return (int) (v1 - v2);
            }
        };

        Set<Map.Entry<String, Float>> entries = vehicleTicksMap.entrySet();
        // Sort method needs a List, so let's first convert Set to List in Java
        List<Map.Entry<String, Float>> listOfEntries = new ArrayList<Map.Entry<String, Float>>(entries);
        listOfEntries.sort(valueComparator);
        for(Map.Entry<String, Float> entry : listOfEntries){
            System.out.println(entry.getKey() + " ==> " + entry.getValue());
        }
    }

    /**
     * Moves vehicles along the route (until all vehicles finish or current tick is spent)
     * @param race - an object of class Race
     * @see JavaAutomationLab.Race # consists of route sections
     */
    public void nextTick(Race race) {

        boolean isFinished = true;

        Route route = race.getRoute();

        List<Vehicle> vehicles = race.getVehicles();

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle currentVehicle = vehicles.get(i);

            float point = currentVehicle.getPoint();

            if (point >= route.getTotalDistance()) {
                continue;
            }

            float speedMax = vehicles.get(i).getEngine().getSpeedMax();

            float currentRouteSectionIndex = (int) point;
            RouteSection currentRouteSection = route.getRouteSections().get((int) currentRouteSectionIndex);

            float tickCapacity = 1F;
            float ticksSpent = 0F;

            while (tickCapacity - ticksSpent > 0F) {

                float currentSpeed = currentVehicle.getSpeedCurrent();
                float currentFrictionCoef = currentRouteSection.getFrictionCoef();
                RouteSection currentVector = route.getRouteSections().get((int) currentRouteSectionIndex);

                currentSpeed = currentFrictionCoef * speedMax;

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

class Race {
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
