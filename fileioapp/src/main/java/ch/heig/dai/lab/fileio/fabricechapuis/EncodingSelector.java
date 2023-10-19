package ch.heig.dai.lab.fileio.fabricechapuis;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

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
        String[] parts = fileName.split("\\.");
        String extension = parts[parts.length - 1];

        if (Objects.equals(extension, "utf8")) return StandardCharsets.UTF_8;
        else if (Objects.equals(extension, "txt")) return StandardCharsets.US_ASCII;
        else if (Objects.equals(extension, "utf16be")) return StandardCharsets.UTF_16BE;
        else if (Objects.equals(extension, "utf16le")) return StandardCharsets.UTF_16LE;

        else return null;
    }
}