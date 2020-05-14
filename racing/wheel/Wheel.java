package JavaAutomationLab.racing.wheel;

import JavaAutomationLab.racing.exception.UnknownRoadMaterialException;

public abstract class Wheel {

    public abstract float getFrictionCoef(String materialName) throws UnknownRoadMaterialException;
}