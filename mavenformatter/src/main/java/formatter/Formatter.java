package formatter;

import formatter.dao.impl.ContentDAOImpl;
import formatter.exception.UnableToCloseException;
import formatter.exception.UnableToReadException;
import formatter.exception.WrongEntryException;
import formatter.ioutils.TextFileReader;
import formatter.user.User;

import java.util.*;

public class Formatter {
    public static void main(String[] args) throws UnableToCloseException, UnableToReadException, WrongEntryException {
        UserActivity userActivity = new UserActivity();
    }




//        TextFileReader textFileReader = new TextFileReader("InputString.txt");
//        String input = textFileReader.read();
//
//        ContentDAOImpl contentDAOImpl = new ContentDAOImpl();
//        contentDAOImpl.createContent(input);
//
//        List<String> inputArray = new ArrayList<>();
//        List<String> nextInputArray = new ArrayList<>();
//        int tabsCount = 0;
//        String[] delimiters = {"(?<=;)", "(?<=\\{)", "(?<=})"};
//        inputArray.add(input);
//        for (String delimiter : delimiters) {
//            for (String inp : inputArray) {
//                String[] inputSplitted = inp.split(delimiter);
//                nextInputArray.addAll(Arrays.asList(inputSplitted));
//            }
//            inputArray = nextInputArray;
//            nextInputArray = new ArrayList<>();
//        }
//        String resultString = "";
//        for (String currentRow : inputArray) {
//            if (currentRow.contains("{")) {
//                resultString = resultString + "\t".repeat(tabsCount) + currentRow + "\n";
//                tabsCount += 1;
//            } else if (currentRow.contains("}")) {
//                tabsCount -= 1;
//                resultString = resultString + "\t".repeat(tabsCount) + currentRow + "\n";
//            } else {
//                resultString = resultString + "\t".repeat(tabsCount) + currentRow + "\n";
//            }
//        }
//        System.out.println(resultString);
//    }
}
