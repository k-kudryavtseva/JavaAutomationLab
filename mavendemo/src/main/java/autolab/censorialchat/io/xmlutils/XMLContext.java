package autolab.censorialchat.io.xmlutils;

import javax.xml.bind.JAXBContext;

public class XMLContext {

    protected JAXBContext context;

    protected XMLContext(JAXBContext context){
        this.context = context;
    }

    public JAXBContext getContext() {
        return context;
    }

    public void setContext(JAXBContext context) {
        this.context = context;
    }
}
