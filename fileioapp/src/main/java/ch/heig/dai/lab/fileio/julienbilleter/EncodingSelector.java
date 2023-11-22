package ch.heig.dai.lab.fileio.julienbilleter;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;



public class EncodingSelector {

    // Map between supporting extensions and corresponding encodings (Charset objects)
    // Altenative : Map.ofEntries( entry(".utf8", StandardCharsets.UTF_8), ...)
    //              but requires importing static java.util.Map.entry;
    private static final Map<String, Charset> extensionMap = Map.of(
        ".utf8", StandardCharsets.UTF_8,
        ".txt", StandardCharsets.US_ASCII,
        ".utf16be", StandardCharsets.UTF_16BE,
        ".utf16le", StandardCharsets.UTF_16LE
    );

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
        // File name
        String filename = file.getName();

        // File extension
        int dotindex = filename.lastIndexOf('.');

        // Return null if no extension is found
        if (dotindex == -1) {
            return null;
        }

        // Extension name
        String extension = filename.substring(dotindex);

        // Get the encoding from the corresponding extension in the map or null (default) if not found
        // Better than get because does not require checking if the extension is found or not !
        return extensionMap.getOrDefault(extension, null);

    }
}