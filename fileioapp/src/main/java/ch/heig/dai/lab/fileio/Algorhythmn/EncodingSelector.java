package ch.heig.dai.lab.fileio.Algorhythmn;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class EncodingSelector {

    private final HashMap<String, Charset> supportedEncode = new HashMap<>();

    /**
     * Constructor with the supported encoding
     */
    public EncodingSelector(){
            supportedEncode.put(".utf8", StandardCharsets.UTF_8);
            supportedEncode.put(".txt", StandardCharsets.US_ASCII);
            supportedEncode.put(".utf16be", StandardCharsets.UTF_16BE);
            supportedEncode.put(".utf16le", StandardCharsets.UTF_16LE);
        }

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


        String filename = file.getName();
        int indexOfPoint = filename.lastIndexOf(".");
        if (indexOfPoint != -1) {
            String extension = filename.substring(indexOfPoint);
            if (supportedEncode.containsKey(extension))
                return supportedEncode.get(extension);
        }
        return null;
    }
}

