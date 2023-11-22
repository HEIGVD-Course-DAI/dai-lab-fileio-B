package ch.heig.dai.lab.fileio.dacc4;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class EncodingSelector {
    private final Map<String, Charset> extensionToCharsetMap;

    public EncodingSelector() {
        extensionToCharsetMap = new HashMap<>();
        extensionToCharsetMap.put("utf8", StandardCharsets.UTF_8);
        extensionToCharsetMap.put("txt", StandardCharsets.US_ASCII);
        extensionToCharsetMap.put("utf16be", StandardCharsets.UTF_16BE);
        extensionToCharsetMap.put("utf16le", StandardCharsets.UTF_16LE);
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
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex >= 0) {
            String extension = fileName.substring(dotIndex + 1).toLowerCase();
            return extensionToCharsetMap.get(extension);
        }

        return null; // Return null if no extension or unrecognized extension
    }
}