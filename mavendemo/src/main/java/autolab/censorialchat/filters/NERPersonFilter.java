package autolab.censorialchat.filters;


import java.io.IOException;

public class NERPersonFilter extends BaseNERFilter{

    private static final String PATH_TO_MODEL = System.getProperty("user.dir") + "/OpenNLP_models/en-ner-person.bin";

    public NERPersonFilter() throws IOException {
        super(PATH_TO_MODEL);
    }
}
