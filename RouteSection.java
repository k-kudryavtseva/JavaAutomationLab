package JavaAutomationLab;

import java.util.HashMap;
import java.util.Map;

public class RouteSection {
    private PointLocation pointStart = new PointLocation();
    private PointLocation pointEnd = new PointLocation();
    private String material;
    float frictionCoef;
    private static Map<String, Float> materialMap = new HashMap<>() {{
        put("Highway",0.9f);
        put("CityRoad",0.7f);
        put("CountryRoad",0.5f);
        put("HaulRoad",0.3f);
    }};

    public RouteSection(String material) throws Exception {
        this.material = material;
        if (materialMap.containsKey(material)) {
            this.frictionCoef = materialMap.get(material);
        } else {
            throw new Exception("Incorrect material name");
        }
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

