package autolab.censorialchat.filters;

public class CapitalizeFilter implements IFilter {
    @Override
    public String filter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
