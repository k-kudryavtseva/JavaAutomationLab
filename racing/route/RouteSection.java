package JavaAutomationLab.racing.route;

import JavaAutomationLab.racing.exception.UnknownRoadMaterialException;

import java.util.HashMap;
import java.util.Map;

public class RouteSection {
    private PointLocation pointStart;
    private PointLocation pointEnd;
    private String material;
    float frictionCoef;
    private static Map<String, Float> materialMap = new HashMap<>() {{
        put("Highway",0.9f);
        put("CityRoad",0.7f);
        put("CountryRoad",0.5f);
        put("HaulRoad",0.3f);
    }};

    public RouteSection(String material, PointLocation pointStart, PointLocation pointEnd) throws UnknownRoadMaterialException {
        if (materialMap.containsKey(material)) {
            this.frictionCoef = materialMap.get(material);
        } else {
            throw new UnknownRoadMaterialException("Incorrect material name");
        }

        this.material = material;
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
    }

    public static Map<String, Float> getMaterialMap() {
        return materialMap;
    }

    public String getMaterial() {
        return material;
    }

    public float getFrictionCoef() {
        return frictionCoef;
    }
}

