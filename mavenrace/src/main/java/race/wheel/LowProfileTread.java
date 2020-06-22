package race.wheel;

import race.exception.UnknownRoadMaterialException;

public class LowProfileTread extends Wheel {

    public float getFrictionCoef(String materialName) throws UnknownRoadMaterialException {
        if (materialName.equals("Highway")) {
            return 1.0f;
        }
        if (materialName.equals("CityRoad")) {
            return 0.75f;
        }
        if (materialName.equals("CountryRoad")) {
            return 0.5f;
        }
        if (materialName.equals("HaulRoad")) {
            return 0.25f;
        } else {
            throw new UnknownRoadMaterialException(materialName);
        }
    }
}
