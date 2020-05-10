package JavaAutomationLab;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class RacingGame {
    public static void main(String[] args) throws Exception {

        Vehicle bugatti = VehicleFactory.createVehicle(
                "JavaAutomationLab.Auto",
                "Bugatti Kristina",
                1.5f,
                new V8Engine(),
                new LowProfileTread()
        );
        Vehicle kamaz = VehicleFactory.createVehicle(
                "JavaAutomationLab.Truck",
                "Kamaz Schumacher",
                7f,
                new V8Engine(),
                new OffRoadTread()
        );
        Vehicle suzuki = VehicleFactory.createVehicle(
                "JavaAutomationLab.Motorbike",
                "Suzuki Mutsukhito",
                0.35f,
                new V4Engine(),
                new OffRoadTread()
        );

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(bugatti);
        vehicles.add(kamaz);
        vehicles.add(suzuki);

        PointLocation pointStart = new PointLocation();
        PointLocation pointEnd = new PointLocation();

        Random randomRoute;
        randomRoute = new Random();
        String[] routeSections = RouteSection.getMaterialMap().keySet().toArray(new String[RouteSection.getMaterialMap().size()]);

        int quantityOfSections = 6;
        List<RouteSection> finalRoute = new ArrayList<>();
        for (int i = 0; i < quantityOfSections; i++) {
            int randomIndex = randomRoute.nextInt(routeSections.length);
            String randomSection = routeSections[randomIndex];
            RouteSection routeSect = new RouteSection(randomSection);
            finalRoute.add(routeSect);
        }

        Route route = new Route(finalRoute);
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
