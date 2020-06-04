package autolab.censorialchat.io.xmlutils;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XMLMarshaller extends XMLContext {

    private Marshaller marshaller;

    public XMLMarshaller(JAXBContext context) throws JAXBException {
        super(context);
        this.marshaller = createMarshaller();
    }

    private Marshaller createMarshaller() throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    public String getXml(Object obj) throws JAXBException {

        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();

    }

    public JAXBContext getContext() {
        return context;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setContext(JAXBContext context) {
        this.context = context;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
}
