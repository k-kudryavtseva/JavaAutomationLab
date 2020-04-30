package JavaAutomationLabSummer2020;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {
    public static void main(String[] args) {
    }
}

class Vehicle {
    private String name;
    private float Point = 0;
    private float speedCurrent = 0;

    public Vehicle(
            String name,
            float pointLocation,
            float speedCurrent,
            ) {
        this.name = name;
        this.Point = pointLocation;
        this.speedCurrent = speedCurrent;
    }

    public String getName() {
        return name;
    }

    public float getPoint() {
        return Point;
    }

    public float getSpeedCurrent() {
        return speedCurrent;
    }

    public void setSpeedCurrent(float speedCurrent) {
        this.speedCurrent = speedCurrent;
    }

    public void setPoint(float Point) {
        this.Point = Point;
    }
}

class Auto extends Vehicle {
    public Auto(int Point, float speedCurrent) {
        super(
                "Auto",
                Point,
                speedCurrent
        );
    }
}

class Bugatti extends Auto {
    public Bugatti(int Point, float speedCurrent) {
        super(
                Point,
                speedCurrent
        );
    }
}

class Dodge extends Auto {
    public Dodge(int Point, float speedCurrent) {
        super(
                Point,
                speedCurrent
        );
    }
}

class Truck extends Vehicle {
    public Truck(int Point, float speedCurrent) {
        super(
                "Truck",
                Point,
                speedCurrent
        );
    }
}

class Kamaz extends Truck {
    public Kamaz(int Point, float speedCurrent) {
        super(
                Point,
                speedCurrent
        );
    }
}

class Mercedes extends Truck {
    public Mercedes(int Point, float speedCurrent) {
        super(
                Point,
                speedCurrent
        );
    }
}

class Motorbike extends Vehicle {
    public Motorbike(int Point, float speedCurrent) {
        super(
                "Motorbike",
                Point,
                speedCurrent
        );
    }
}

class Suzuki extends Motorbike {
    public Suzuki(int Point, float speedCurrent) {
        super(
                Point,
                speedCurrent
        );
    }
}

class Yamaha extends Motorbike {
    public Yamaha(int Point, float speedCurrent) {
        super(
                Point,
                speedCurrent
        );
    }
}

class Route {
    private ArrayList<RouteSection> routeSections;

    public Route(ArrayList<RouteSection> routeSections) {
        this.routeSections = routeSections;
    }

    public float getTotalDistance() {
        return routeSections.size();
    }
}

class Point {
    private int x = 0;
    private int y = 0;
}

class RouteSection {
    private Point pointStart = new Point();
    private Point pointEnd = new Point();
    private String materialName;
    private float frictionCoef;

    public RouteSection(Point pointStart, Point pointEnd, String materialName, float frictionCoef) {
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
    public Highway(Point pointStart, Point pointEnd, String materialName, float frictionCoef) {
        super(
                pointStart,
                pointEnd,
                "Highway",
                0.9F
        );
    }
}

class CityRoad extends RouteSection {
    public CityRoad(Point pointStart, Point pointEnd, String materialName, float frictionCoef) {
        super(
                pointStart,
                pointEnd,
                "CityRoad",
                0.7F
        );
    }
}

class CountryRoad extends RouteSection {
    public CountryRoad(Point pointStart, Point pointEnd, String materialName, float frictionCoef) {
        super(
                pointStart,
                pointEnd,
                "CountryRoad",
                0.5F
        );
    }
}

class HaulRoad extends RouteSection {
    public HaulRoad(Point pointStart, Point pointEnd, String materialName, float frictionCoef) {
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

interface IGarage {
    public void updateVehiclePart();

    public void addVehiclePart();

    public void deleteVehiclePart();
}

class Supervisor {
    private int currentTick;

    public float getCurrentTicks() {
            return currentTick;
        }
        public void updateCurrentTicks(float ticks) {
            this.currentTick += ticks;
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
        }
    }
}

class Race extends Supervisor {
    private Route route;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Engine> engines;
    private boolean isFinished;

    public Race(Route route, ArrayList<Vehicle> vehicles, ArrayList<Engine> engines) {
        this.route = route;
        this.vehicles = vehicles;
        this.engines = engines;
        this.isFinished = false;
    }

    public void sortVehiclesByTime() {
        List<Vehicle> sortedVehicles = new ArrayList<>(vehicles);

        for (int i = 0; i < sortedVehicles.size(); i++) {
            for (int j = 0; j < sortedVehicles.size(); j++) {
                if (sortedVehicles.get(i).getCurrentTicks() > sortedVehicles.get(j).getCurrentTicks()) {
                    Vehicle temp = sortedVehicles.get(j);
                    sortedVehicles.set(j, sortedVehicles.get(i));
                    sortedVehicles.set(i, temp);
                }
            }
        }

        for (int i = 0; i < sortedVehicles.size(); i++) {
            System.out.println(Integer.toString(i) + " " + sortedVehicles.get(i).getName());
        }
    }

    public void nextTick() {

        boolean isFinished = true;

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle currentVehicle = vehicles.get(i);
            float Point = currentVehicle.getPoint();

            if (Point >= route.getTotalDistance()) {
                continue;
            }

            int currentRouteSectionIndex = (int) Point;
            RouteSection currentRouteSection = route.routeSections[currentRouteSectionIndex];

            float tickCapacity = 1F;
            float ticksSpent = 0F;

            while (tickCapacity - ticksSpent > 0F) {

                float currentSpeed = currentVehicle.getSpeedCurrent();
                float currentFrictionCoef = currentRouteSection.getFrictionCoef();
                RouteSection currentVector = route.routeSections[currentRouteSectionIndex];

                float currentSpeed = currentFrictionCoef * speedMax;

                if (Point + currentSpeed * (tickCapacity - ticksSpent) > currentRouteSectionIndex + 1) {

                    currentRouteSectionIndex += 1; // переходим на следующий участок
                    ticksSpent = ((float) currentRouteSectionIndex - Point) / currentSpeed;
                    Point = (float) currentRouteSectionIndex;

                } else {

                    Point += currentSpeed * (tickCapacity - ticksSpent);
                    ticksSpent = tickCapacity;
                }

                if (Point < route.getTotalDistance()) {
                    isFinished = false;
                } else {
                    break;
                }

                currentVehicle.setPoint(Point);
                currentVehicle.updateCurrentTicks(ticksSpent);

            }

            if (isFinished) {
                this.isFinished = true;
                endRacing();
            }
        }
    }
}