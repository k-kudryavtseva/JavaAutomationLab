package formatter;

import formatter.dao.impl.ContentDAOImpl;
import formatter.exception.UnableToCloseException;
import formatter.exception.UnableToReadException;
import formatter.ioutils.TextFileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Formatter {
    public static void main(String[] args) throws UnableToCloseException, UnableToReadException {
        TextFileReader textFileReader = new TextFileReader("InputString.txt");
        String input = textFileReader.read();

        ContentDAOImpl contentDAOImpl = new ContentDAOImpl();
        contentDAOImpl.createContent(input);

        List<String> inputArray = new ArrayList<>();
        List<String> nextInputArray = new ArrayList<>();
        int tabsCount = 0;
        String[] delimiters = {"(?<=;)", "(?<=\\{)", "(?<=})"};
        inputArray.add(input);
        for (String delimiter : delimiters) {
            for (String inp : inputArray) {
                String[] inputSplitted = inp.split(delimiter);
                nextInputArray.addAll(Arrays.asList(inputSplitted));
            }
            inputArray = nextInputArray;
            nextInputArray = new ArrayList<>();
        }
        String resultString = "";
        for (String currentRow : inputArray) {
            if (currentRow.contains("{")) {
                resultString = resultString + "\t".repeat(tabsCount) + currentRow + "\n";
                tabsCount += 1;
            } else if (currentRow.contains("}")) {
                tabsCount -= 1;
                resultString = resultString + "\t".repeat(tabsCount) + currentRow + "\n";
            } else {
                resultString = resultString + "\t".repeat(tabsCount) + currentRow + "\n";
            }
        }
        System.out.println(resultString);
    }
}
