package JavaAutomationLab;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RacingGame {
    public static void main(String[] args) {

        Engine bugattiEngine = new Engine(0.7f);
        Bugatti bugatti1 = new Bugatti("Bugatti1", 0, bugattiEngine);

        Engine kamazEngine = new Engine(0.3f);
        Kamaz kamaz1 = new Kamaz("Kamaz 1", 0, kamazEngine);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(bugatti1);
        vehicles.add(kamaz1);

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
        Race race = new Race(route, (ArrayList<Vehicle>) vehicles);

        System.out.println("RouteSection includes fields of");
        race.printAllFields(routeSections.get(0).getClass());

        System.out.println("Race includes fields of");
        race.printAllFields(race.getClass());

        Scanner scanner = new Scanner(System.in);

        race.startRacing();

        while (!race.getIsFinished()) {

            System.out.println("Press Enter to continue");
            scanner.nextLine();
            race.nextTick();

        }
    }
}

class Vehicle {
    private String name;
    private float point;
    private float speedCurrent;
    private Engine engine;
    private float ticksCurrent;

    public Vehicle(
            String name,
            float speedCurrent,
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

    public void updateCurrentTicks(float ticksSpent) {
        this.ticksCurrent = ticksSpent;
    }

    public float getCurrentTicks() {
        return ticksCurrent;
    }
}

class Auto extends Vehicle {

    public Auto(String name, float speedCurrent, Engine engine) {
        super(name, speedCurrent, engine);
    }
}

class Bugatti extends Auto {
    public Bugatti(String name, float speedCurrent, Engine engine) {
        super(
                name,
                speedCurrent,
                engine
        );
    }
}

class Dodge extends Auto {
    public Dodge(String name, float speedCurrent, Engine engine) {
        super(
                name,
                speedCurrent,
                engine
        );
    }
}

class Truck extends Vehicle {
    public Truck(String name, float speedCurrent, Engine engine) {
        super(
                name,
                speedCurrent,
                engine
        );
    }
}

class Kamaz extends Truck {
    public Kamaz(String name, float speedCurrent, Engine engine) {
        super(
                name,
                speedCurrent,
                engine
        );
    }
}

class Mercedes extends Truck {
    public Mercedes(String name, float speedCurrent, Engine enginee) {
        super(
                name,
                speedCurrent,
                enginee
        );
    }
}

class Motorbike extends Vehicle {
    public Motorbike(String name, float speedCurrent, Engine engine) {
        super(
                name,
                speedCurrent,
                engine
        );
    }
}

class Suzuki extends Motorbike {
    public Suzuki(String name, float speedCurrent, Engine engine) {
        super(
                name,
                speedCurrent,
                engine
        );
    }
}

class Yamaha extends Motorbike {
    public Yamaha(String name, float speedCurrent, Engine engine) {
        super(
                name,
                speedCurrent,
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

class Supervisor {
    private int currentTick;

    public float getCurrentTicks() {
        return currentTick;
    }

    public void updateCurrentTicks(float ticks) {
            this.currentTick += ticks;
    }
}

class Race {
    private Route route;
    private ArrayList<Vehicle> vehicles;
    private boolean isFinished;

    public Race(Route route, ArrayList<Vehicle> vehicles) {
        this.route = route;
        this.vehicles = vehicles;
        this.isFinished = false;
    }

    public boolean getIsFinished() {
        return isFinished;
    }
    public static void printSortedVehiclesByTime(ArrayList<Vehicle> vehicles) {

        // copying for security reasons
        List<Vehicle> sortedVehicles = new ArrayList<>(vehicles);

        for (int i = 0; i < sortedVehicles.size(); i++) {
            for (int j = i + 1; j < sortedVehicles.size(); j++) {
                if (sortedVehicles.get(i).getCurrentTicks() > sortedVehicles.get(j).getCurrentTicks()) {
                    Vehicle temp = sortedVehicles.get(j);
                    sortedVehicles.set(j, sortedVehicles.get(i));
                    sortedVehicles.set(i, temp);
                }
            }
        }

        for (int i = 0; i < sortedVehicles.size(); i++) {
            System.out.println(Integer.toString(i + 1) + " " + sortedVehicles.get(i).getName());
        }
    }

    public void startRacing() {
        System.out.println("Start!");
    }

    public void endRacing() {
        System.out.println("Race is over!");
        announceWinner();
    }

    public void announceWinner() {
        System.out.println("Winner is");
        printSortedVehiclesByTime(this.vehicles);
    }

    public void nextTick() {

        boolean isFinished = true;

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle currentVehicle = vehicles.get(i);
            float point = currentVehicle.getPoint();

            if (point >= route.getTotalDistance()) {
                continue;
            }

            float speedMax = vehicles.get(i).getEngine().getSpeedMax();

            float currentRouteSectionIndex = point;
            RouteSection currentRouteSection = route.getRouteSections().get((int)currentRouteSectionIndex);

            float tickCapacity = 1F;
            float ticksSpent = 0F;

            while (tickCapacity - ticksSpent > 0F) {

                float currentSpeed = currentVehicle.getSpeedCurrent();
                float currentFrictionCoef = currentRouteSection.getFrictionCoef();
                RouteSection currentVector = route.getRouteSections().get((int)currentRouteSectionIndex);

                currentSpeed = currentFrictionCoef * speedMax;

                if (point + currentSpeed * (tickCapacity - ticksSpent) > currentRouteSectionIndex + 1) {

                    currentRouteSectionIndex += 1; // переходим на следующий участок
                    ticksSpent = ((float) currentRouteSectionIndex - point) / currentSpeed;
                    point = (float) currentRouteSectionIndex;

                } else {

                    point += currentSpeed * (tickCapacity - ticksSpent);
                    ticksSpent = tickCapacity;
                }

                if (point < route.getTotalDistance()) {
                    isFinished = false;
                } else {
                    break;
                }

                currentVehicle.setPoint(point);
                currentVehicle.updateCurrentTicks(ticksSpent);

            }

            if (isFinished) {
                this.isFinished = true;
                endRacing();
            }
        }
    }

    public void printAllFields(Class<?> myClass) {
        Field[] fieldsClass = myClass.getDeclaredFields();
        Field[] fieldsSuperClass = myClass.getSuperclass().getDeclaredFields();

        System.out.println("Class:");
        for (Field field: fieldsClass) {
            System.out.println(field.getName() + " " + field.getType());
        }

        System.out.println("Superclass:");
        for (Field field: fieldsSuperClass) {
            System.out.println(field.getName() + " " + field.getType());
        }
    }
}