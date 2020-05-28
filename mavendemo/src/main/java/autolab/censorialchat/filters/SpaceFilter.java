package autolab.censorialchat.filters;

public class SpaceFilter implements IFilter {
    public static String deleteSpace(String str) {
        return str.trim().replaceAll("\\s{2,}", " ");
    }

    @Override
    public String filter(String string) {
        return deleteSpace(string);
    }
}
