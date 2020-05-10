package JavaAutomationLab;

public class OffRoadTread extends Wheel {

    public float getFrictionCoef(String materialName) throws UnknownRoadMaterialException {

        if (materialName.equals("Highway")) {
            return .25f;
        } if (materialName.equals("CityRoad")) {
            return .5f;
        } if (materialName.equals("CountryRoad")) {
            return .75f;
        } if (materialName.equals("HaulRoad")) {
            return 1f;
        }
        else {
            throw new UnknownRoadMaterialException(materialName);
        }
    }
}
