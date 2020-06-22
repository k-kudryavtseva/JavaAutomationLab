package race.main;

import race.engine.Engine;
import race.engine.V4Engine;
import race.engine.V8Engine;
import race.exception.InvalidPointLocationException;
import race.exception.InvalidVehiclePropertiesException;
import race.ioutils.PropertyFileReader;
import race.ioutils.TextFileReader;
import race.race.Race;
import race.route.PointLocation;
import race.route.Route;
import race.route.RouteSection;
import race.supervisor.Supervisor;
import race.vehicle.Vehicle;
import race.vehicle.VehicleFactory;
import race.wheel.LowProfileTread;
import race.wheel.OffRoadTread;
import race.wheel.Wheel;

import java.io.File;
import java.util.*;

public class RacingGame {

    private static final String PATH = "src" + File.separator + "JavaAutomationLab" + File.separator;

    public static void main(String[] args) throws Exception {

        TextFileReader textFileReader = new TextFileReader(PATH + "Points.txt");

        List<PointLocation> pointLocations = new ArrayList<>();

        for (String points : textFileReader.read().split("\n")) {

            String[] pointsSplitted = points.split(",");

            if (pointsSplitted.length != 2) {
                throw new InvalidPointLocationException(
                        "Wrong number of coordinates! 2 expected, but got " + pointsSplitted.length
                );
            }

            pointLocations.add(
                    new PointLocation(
                            Integer.parseInt(pointsSplitted[0]),
                            Integer.parseInt(pointsSplitted[1])
                    )
            );
        }

        PropertyFileReader propertiesReader = new PropertyFileReader(PATH + "race.properties");

        propertiesReader.read();

        String[] vehicleNames = propertiesReader.getPropertyValue("vehicleNames").split(",");
        String[] vehicleVehicleNames = propertiesReader.getPropertyValue("vehicleVehicleNames").split(",");
        String[] vehicleMasses = propertiesReader.getPropertyValue("vehicleMasses").split(",");

        // todo: factories for engines and wheels
        Engine[] engines = {new V8Engine(), new V8Engine(), new V4Engine()};
        Wheel[] wheels = {new LowProfileTread(), new OffRoadTread(), new OffRoadTread()};

        if ((vehicleMasses.length != vehicleNames.length) || (vehicleMasses.length != vehicleVehicleNames.length)) {
            int[] temp = {vehicleMasses.length, vehicleNames.length, vehicleVehicleNames.length};
            throw new InvalidVehiclePropertiesException(
                    "Expected properties arrays sizes to be equal to each other but got " + Arrays.toString(temp)
            );
        }

        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < vehicleNames.length; i++) {
            Vehicle currentVehicle = VehicleFactory.createVehicle(
                    vehicleNames[i],
                    vehicleVehicleNames[i],
                    Float.parseFloat(vehicleMasses[i]),
                    engines[i],
                    wheels[i]
            );
            vehicles.add(currentVehicle);
        }

        Random randomRoute;
        randomRoute = new Random();
        String[] routeSections = RouteSection.getMaterialMap().keySet().toArray(new String[0]);

        List<RouteSection> finalRoute = new ArrayList<>();

        for (int i = 0; i < pointLocations.size() - 1; i++) {
            int randomIndex = randomRoute.nextInt(routeSections.length);
            String randomSection = routeSections[randomIndex];
            RouteSection routeSect = new RouteSection(randomSection, pointLocations.get(i), pointLocations.get(i+1));
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
