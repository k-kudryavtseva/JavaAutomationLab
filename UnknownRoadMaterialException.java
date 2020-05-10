package JavaAutomationLab;

public class UnknownRoadMaterialException extends Exception {
    public UnknownRoadMaterialException (String materialName) {
        super(materialName + " is unsupported material name for this type of wheel!");
    }
}