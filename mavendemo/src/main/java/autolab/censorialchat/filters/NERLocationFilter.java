package autolab.censorialchat.filters;


import java.io.IOException;

public class NERLocationFilter extends BaseNERFilter{

    private static final String pathToModel = System.getProperty("user.dir") + "/OpenNLP_models/en-ner-location.bin";

    public NERLocationFilter() throws IOException {
        super(pathToModel);
    }
}
