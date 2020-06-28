package race.wheel;

import race.exception.UnknownRoadMaterialException;

public abstract class Wheel {

    public abstract float getFrictionCoef(String materialName) throws UnknownRoadMaterialException;
}