package ch.heig.dai.lab.fileio.romainfleury;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;

public class EncodingSelector {

    /**
     * Get the encoding of a file based on its extension.
     * The following extensions are recognized:
     *   - .utf8:    UTF-8
     *   - .txt:     US-ASCII
     *   - .utf16be: UTF-16BE
     *   - .utf16le: UTF-16LE
     *
     * @param file the file to get the encoding from
     * @return the encoding of the file, or null if the extension is not recognized
     */
    public Charset getEncoding(File file) {
        // TODO: implement the method body here
        String[] extName = {"utf8", "txt", "utf16be", "utf16le"};
        String[] charsetName = {"UTF-8", "US-ASCII", "UTF-16BE", "UTF-16LE"};

        String filename = file.getName();

        int index = filename.lastIndexOf('.');
        if(index > 0){
            String extension = filename.substring(index+1);
            index = Arrays.asList(extName).indexOf(extension);
            if(index != -1){
                return Charset.forName(charsetName[index]);
            }
        }

        return null;
    }
}