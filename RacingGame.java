package JavaAutomationLab;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class RacingGame {
    public static void main(String[] args) {

        Vehicle bugatti = VehicleFactory.createVehicle(
                "JavaAutomationLab.Auto",
                "Bugatti Kristina",
                1.5f,
                new V4Engine(),
                new LowProfileTread()
        );
        Vehicle kamaz = VehicleFactory.createVehicle(
                "JavaAutomationLab.Truck",
                "Kamaz Schumacher",
                10f,
                new V8Engine(),
                new OffRoadTread()
        );
        Vehicle suzuki = VehicleFactory.createVehicle(
                "JavaAutomationLab.Motorbike",
                "Suzuki Mutsukhito",
                0.2f,
                new V6Engine(),
                new OffRoadTread()
        );

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
            try {
                String input = scanner.nextLine();

                if (!input.equals("")) {
                    throw new RuntimeException("Unrecognized input symbol!");
                }

                race.getSupervisor().nextTick(race);
                // посмотреть, к какому типу ошибки относится неверное нажатие клавиши
            } catch (Exception e) {
                System.out.println("Please, press Enter to continue");
            }
        }
    }
}
