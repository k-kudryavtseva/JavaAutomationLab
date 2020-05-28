package autolab.censorialchat.filters;

public class SpaceFilter {
    public static String deleteSpace(String str) {
        return str.trim().replaceAll("\\s{2,}", " ");
    }
}
