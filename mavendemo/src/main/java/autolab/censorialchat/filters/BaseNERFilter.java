package autolab.censorialchat.filters;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class BaseNERFilter {

    InputStream inputStream;
    TokenNameFinderModel model;
    NameFinderME nameFinder;
    SentenceTokenizer sentenceTokenizer;

    public BaseNERFilter(String pathToModel) throws IOException {
        inputStream = new FileInputStream(pathToModel);
        model = new TokenNameFinderModel(inputStream);
        nameFinder = new NameFinderME(model);
        sentenceTokenizer = new SentenceTokenizer();
    }

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public String capitalizeNamedEntities(String str) {
        String[] tokens = sentenceTokenizer.tokenize(str);
        String[] tokensOriginal = tokens.clone();

        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = capitalize(tokens[i]);
        }

        Span[] nerSpans = nameFinder.find(tokens);

        for (int i = 1; i < tokens.length; i++) {
            tokens[i] = tokens[i].toLowerCase();
        }

        tokensOriginal[0] = capitalize(tokensOriginal[0]);
        for (Span s: nerSpans) {
            for (int i = s.getStart(); i < s.getEnd(); i++) {
                tokensOriginal[i] = capitalize(tokensOriginal[i]);
            }
        }

        String result = "";

        for (String token: tokensOriginal) {
            if (Pattern.matches("\\p{Punct}", token)) {
                result = result + token;
            }
            else {
                result = result + " " + token;
            }
        }
        return result;
    }
}
