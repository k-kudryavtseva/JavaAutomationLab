package autolab.censorialchat.io.impl.file;

import autolab.censorialchat.io.base.BaseReader;
import autolab.censorialchat.io.exception.UnableToReadException;
import autolab.censorialchat.io.interfaces.Reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TextFileReader extends BaseReader implements Reader {
    public TextFileReader(File file) {
        super(file);
    }

    public TextFileReader(String path) {
        super(path);
    }

    public String read() throws UnableToReadException {
        try(FileReader reader = new FileReader(this.file))
        {
            char[] buf = new char[256];
            int c;
            while((c = reader.read(buf))>0){

                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }

            }
            return new String(buf);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnableToReadException("");
        }
    }
}
