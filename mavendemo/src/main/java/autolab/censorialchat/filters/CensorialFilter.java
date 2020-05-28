package autolab.censorialchat.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Pattern;

public class CensorialFilter {
    private static HashSet<String> taboo;
    private static final String PATH_TO_BAD_WORDS = System.getProperty("user.dir") + "/src/main/java/autolab/censorialchat/swearwords.txt";

    public static void readBadWords() {
        taboo = new HashSet<String>();
        try (FileReader reader = new FileReader(PATH_TO_BAD_WORDS);
             BufferedReader bufferedReader = new BufferedReader(reader))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                taboo.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String replaceSymbol(String badword) {
        Random random = new Random();
        int index = random.nextInt(badword.length());
        return badword.replaceFirst(badword.substring(index, index + 1), "(•_•)");
    }

    public static String validateMessage(String message) {
        if (Pattern.matches(".*\\p{InCyrillic}.*", message)) {
            return "Cyrillic message which we can't show";
        } else {
            String modifiedMessage = message;
            for (String badword : taboo) {
                if (modifiedMessage.contains(badword)) {
                    modifiedMessage = modifiedMessage.replace(badword, replaceSymbol(badword));
                }
            }
            return modifiedMessage;
        }
    }
}
