package ch.heig.dai.lab.fileio.dominiksaul;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class EncodingSelector {
    private final Map<String, Charset> encodingMap = Map.of(
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
        String filename = file.getName();
        int index = filename.lastIndexOf('.');

        // In case the file doesn't have an extension (index == -1)
        // or the file is hidden and doesn't have an extension (index == 0)
        if (index <= 0) return null;

        String extension = filename.substring(index);

        // returns null if encoding not found
        return encodingMap.get(extension);
    }
}