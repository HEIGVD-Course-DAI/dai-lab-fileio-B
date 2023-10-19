package ch.heig.dai.lab.fileio.maximesch;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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

    private static final Map<String, Charset> dotExtToExt = Map.of(
            ".utf8" , StandardCharsets.UTF_8,
            ".txt", StandardCharsets.US_ASCII,
            ".utf16be", StandardCharsets.UTF_16BE,
            ".utf16le", StandardCharsets.UTF_16LE
    );

    public Charset getEncoding(File file) {

        String fileName = file.getName();
        int lastDotBeforeExt = fileName.lastIndexOf('.');

        if(lastDotBeforeExt == -1){ // no '.' found
            return null;
        }

        String extensionStr = fileName.substring(lastDotBeforeExt);
        if(dotExtToExt.containsKey(extensionStr)){
            return dotExtToExt.get(extensionStr);
        }

        //if we still have not returned anything at that point, there is a "."
        // but no valid extension after and we return null
        return null;
    }
}