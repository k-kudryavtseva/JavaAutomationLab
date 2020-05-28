package autolab.censorialchat.filters;


import java.io.IOException;

public class NERPersonFilter extends BaseNERFilter{

    private static final String pathToModel = System.getProperty("user.dir") + "/OpenNLP_models/en-ner-person.bin";

    public NERPersonFilter() throws IOException {
        super(pathToModel);
    }
}
