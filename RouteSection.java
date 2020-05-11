package JavaAutomationLab;

public class RouteSection {
    private PointLocation pointStart = new PointLocation();
    private PointLocation pointEnd = new PointLocation();
    private String materialName;

    public RouteSection(PointLocation pointStart, PointLocation pointEnd, String materialName, float frictionCoef) {
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }
}
