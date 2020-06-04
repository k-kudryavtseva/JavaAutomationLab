package autolab.censorialchat.filters;

public class CapitalizeFilter implements IFilter {
    @Override
    public String filter(String string) {
        string = BaseNERFilter.capitalize(string);
        return string;
    }
}
