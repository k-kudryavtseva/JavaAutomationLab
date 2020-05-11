package JavaAutomationLab;

public class HaulRoad extends RouteSection {
    public HaulRoad(PointLocation pointStart, PointLocation pointEnd) {
        super(
                pointStart,
                pointEnd,
                "HaulRoad",
                0.3F
        );
    }
}
