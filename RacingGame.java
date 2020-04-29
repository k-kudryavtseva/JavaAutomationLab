package JavaAutomationLabSummer2020;
 /* машины едут только вперёд, и пройденные участки нам не нужны (стек ? очередь) - опционально
    по поводу трекинга: предлагаю поставить на каждый участок штук по 20-30 точек,
    и чтобы точки отстояли друг от друга на равном расстоянии, конечными будут считаться самая левая и самая правая
    сделайте, чтобы в точках, помимо координат, был транспорт
    2d означает, что мы работаем двумерной системе координат
    в машине можно хранить текущую скорость, которая зависит от коэффициента, а кф - от материала дроги*/

import java.util.ArrayList;

public class RacingGame {
    public static void main(String[] args) {
    }
}

class Vehicle {
    private String name;
    private float currentPoint = 0;
    private float speedCurrent = 0;
    private float speedMax;
    private int currentTick;

    public Vehicle(
            String name,
            float pointLocation,
            float speedCurrent,
            float speedMax,
            float currentTick
    ) {
        this.name = name;
        this.currentPoint = pointLocation;
        this.speedCurrent = speedCurrent;
        this.speedMax = speedMax;
        this.currentTick = 0;
    }

    public String getName() { return name; }

    public float getCurrentPoint() {
        return currentPoint;
    }

    public float getSpeedCurrent() {
        return speedCurrent;
    }

    public float getSpeedMax() { return speedMax; }

    public float getCurrentTicks() {
        return currentTick;
    }

    public void setSpeedCurrent(float speedCurrent) {
        this.speedCurrent = speedCurrent;
    }

    public void setCurrentPoint(float currentPoint) {
        this.currentPoint = currentPoint;
    }

    public void setSpeedMax(float speedMax) { this.speedMax = speedMax; }

    public void updateCurrentTicks(float ticks) {
        this.currentTick += ticks;
    }
}

class Auto extends Vehicle {
    public Auto(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                "Auto",
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
        );
    }
}

class Bugatti extends Auto {
    public Bugatti(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
        );
    }
}

class Dodge extends Auto {
    public Dodge(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
        );
    }
}

class Truck extends Vehicle {
    public Truck(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                "Truck",
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
        );
    }
}

class Kamaz extends Truck {
    public Kamaz(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
        );
    }
}

class Mercedes extends Truck {
    public Mercedes(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
        );
    }
}

class Motorbike extends Vehicle {
    public Motorbike(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                "Motorbike",
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
        );
    }
}

class Suzuki extends Motorbike {
    public Suzuki(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
        );
    }
}

class Yamaha extends Motorbike {
    public Yamaha(int currentPoint, float speedCurrent, float speedMax, int currentTick) {
        super(
                currentPoint,
                speedCurrent,
                speedMax,
                currentTick
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

    public String getMaterialName() { return materialName; }

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

class HaulRoad extends RouteSection { // карьерная дорога
    public HaulRoad(Point pointStart, Point pointEnd, String materialName, float frictionCoef) {
        super(
                pointStart,
                pointEnd,
                "Ferry",
                0.3F
        );
    }
}

class Wheel {
    private float frictionCoef;

    public Wheel() {}
}

class Engine {
    public Engine() {}
}

interface IGarage {
    public void updateVehiclePart();

    public void addVehiclePart();

    public void deleteVehiclePart();
}

interface ISupervisor {
    public void startRacing();

    public void endRacing();

    public void announceWinner();
}

class Race implements ISupervisor {
    private Route route;
    private ArrayList<Vehicle> vehicles;
    private boolean isFinished;

    public Race(Route route, ArrayList<Vehicle> vehicles) {
        this.route = route;
        this.vehicles = vehicles;
        this.isFinished = false;
    }

    public void sortVehiclesByTime() {
        ArrayList<Vehicle> sortedVehicles = new ArrayList<>(vehicles);

        for (int i = 0; i < sortedVehicles.size(); i++) {
            for (int j = 0; j < sortedVehicles.size(); j++) {
                if (sortedVehicles.get(i).getCurrentTicks() > sortedVehicles.get(j).getCurrentTicks()) {
                    Vehicle temp = sortedVehicles.get(i);
                    sortedVehicles.set(sortedVehicles.get(j), i);
                    sortedVehicles.set(temp, j);
                }
            }
        }

        for (int i = 0; i < sortedVehicles.size(); i++) {
            System.out.println(str(i) + " " + sortedVehicles.get(i).getName());
        }
    }

    public void nextTick() {

        boolean isFinished = true;

        for(int i = 0; i < vehicles.size(); i++) {
            Vehicle currentVehicle = vehicles.get(i);
            float currentPoint = currentVehicle.getCurrentPoint();

            if (currentPoint >= route.getTotalDistance()) {
                continue;
            }

            int currentRouteSectionIndex = (int) currentPoint;
            RouteSection currentRouteSection = route.routeSections[currentRouteSectionIndex];

            float tickCapacity = 1.0F;
            float ticksSpent = 0.0F;

            while(tickCapacity - ticksSpent > 0.0) {

                float currentSpeed = currentVehicle.getSpeedCurrent();
                float currentFrictionCoef = currentRouteSection.getFrictionCoef();
                RouteSection currentVector = route.routeSections[currentRouteSectionIndex];

                float currentSpeed = currentFrictionCoef * speedMax;

                if (currentPoint + currentSpeed * (tickCapacity - ticksSpent) > currentRouteSectionIndex + 1) {

                    currentRouteSectionIndex += 1; // переходим на следующий участок
                    ticksSpent = ((float) currentRouteSectionIndex - currentPoint) / currentSpeed;
                    currentPoint = (float) currentRouteSectionIndex;

                }
                else {

                    currentPoint += currentSpeed * (tickCapacity - ticksSpent);
                    ticksSpent = tickCapacity;
                }

                if(currentPoint < route.getTotalDistance()) {
                    isFinished = false;
                }
                else {
                    break;
                }

            }

            currentVehicle.setCurrentPoint(currentPoint);
            currentVehicle.updateCurrentTicks(ticksSpent);

        }

        if (isFinished) {
            this.isFinished = true;
            endRacing();
        }
    }

    @Override
    public void startRacing() {
        System.out.println("Start!");
    }

    @Override
    public void endRacing() {
        System.out.println("Race is over!");
        announceWinner();
    }

    @Override
    public void announceWinner() {
        System.out.println("Winner is");
    }
}