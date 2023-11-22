package ch.heig.dai.lab.fileio.AlessioGiuliano;

import java.io.File;
import java.nio.charset.Charset;
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
    public Charset getEncoding(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index > 0)
        {
            String extension = fileName.substring(index);
            if (EXTENSION_TO_CHARSET.containsKey(extension))
            {
                return Charset.forName(EXTENSION_TO_CHARSET.get(extension));
            }
        }
        return null;
    }
    private final static Map<String, String> EXTENSION_TO_CHARSET = Map.of(
        ".utf8", "UTF-8",
        ".txt", "US-ASCII", 
        ".utf16be", "UTF-16BE",
        ".utf16le", "UTF-16LE");
}