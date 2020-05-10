package JavaAutomationLab;

import java.util.List;

public class Route {
    private List<RouteSection> routeSections;

    public Route(List<RouteSection> routeSections) {
        this.routeSections = routeSections;
    }

    public List<RouteSection> getRouteSections() {
        return routeSections;
    }

    public float getTotalDistance() {
        return routeSections.size();
    }
}
