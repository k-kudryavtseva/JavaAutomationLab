package autolab.censorialchat.filters;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SentenceTokenizer {
    InputStream inputStream;
    TokenizerModel tokenModel;
    TokenizerME tokenizer;

    public SentenceTokenizer() throws IOException {
        inputStream = new FileInputStream(System.getProperty("user.dir") + "/OpenNLP_models/en-token.bin");
        tokenModel = new TokenizerModel(inputStream);
        tokenizer = new TokenizerME(tokenModel);
    }

    public String[] tokenize(String str) {
        String tokens[] = tokenizer.tokenize(str);
        return tokens;
    }
}
