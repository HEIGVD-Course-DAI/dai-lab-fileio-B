package ch.heig.dai.lab.fileio.LeoR;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class EncodingSelector {

    // Followed your advice and used map to avoid the repetition of "else if" instructions
    private final Map<String, Charset> encodingMap;
    public EncodingSelector()
    {
        encodingMap = new HashMap<>();
        encodingMap.put(".utf8", StandardCharsets.UTF_8);
        encodingMap.put(".txt", StandardCharsets.US_ASCII);
        encodingMap.put(".utf16be", StandardCharsets.UTF_16BE);
        encodingMap.put(".utf16le", StandardCharsets.UTF_16LE);
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
    public Charset getEncoding(File file)
    {
        // TODO: implement the method body here

        if (file == null) {return null;}

        String fileName = file.getName();
        for (String extension : encodingMap.keySet())
        {
            if (fileName.endsWith(extension))
            {
                return encodingMap.get(extension);
            }
        }

        // If we're here, the extension hasn't been recognized from the map
        return null;
    }
}