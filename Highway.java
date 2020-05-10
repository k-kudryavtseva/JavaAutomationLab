package JavaAutomationLab;

public class Highway extends RouteSection {

    private static float frictionCoef = 0.9f;

    public Highway(PointLocation pointStart, PointLocation pointEnd) {
        super(
                pointStart,
                pointEnd,
                "Highway",
                0.9F
        );
    }
}
