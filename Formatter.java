package JavaAutomationLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Formatter {
    public static void main(String[] args) {
        String input = "public class NoSuchTransport extends Exception{public NoSuchTransport(String message) {super(message);if(aaa){aaa+b=5;return b;}}}";
        List<String> inputArray = new ArrayList<>();
        List<String> nextInputArray = new ArrayList<>();
        int tabsCount = 0;
        String[] delimiters = {"(?<=;)", "(?<=\\{)", "(?<=})"};
        inputArray.add(input);
        for (String delimiter: delimiters) {
            for (String inp: inputArray) {
                String[] inputSplitted = inp.split(delimiter);
                nextInputArray.addAll(Arrays.asList(inputSplitted));
            }
            inputArray = nextInputArray;
            nextInputArray = new ArrayList<>();
        }
        String resultString = "";
        for (String currentRow : inputArray) {
            if(currentRow.contains("{")) {
                resultString = resultString + "\t".repeat(tabsCount) + currentRow + "\n";
                tabsCount += 1;
            }
            else if (currentRow.contains("}")) {
                tabsCount -= 1;
                resultString = resultString + "\t".repeat(tabsCount) + currentRow + "\n";
            }
            else {
                resultString = resultString + "\t".repeat(tabsCount)  + currentRow + "\n";
            }
        }
        System.out.println(resultString);
    }
}
