package ch.heig.dai.lab.fileio.dariovas;

import javax.swing.*;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncodingSelector {
    /**
     * Gets the charset from a file extension
     * @param fileExtension the extension to get the corresponding charset
     * @return the extension charset, or null if the charset is not recognize.
     */
    private Charset getCharset(String fileExtension){
        return switch (fileExtension) {
            case "utf8" -> StandardCharsets.UTF_8;
            case "txt" -> StandardCharsets.US_ASCII;
            case "utf16be" -> StandardCharsets.UTF_16BE;
            case "utf16le" -> StandardCharsets.UTF_16LE;
            default -> null;
        };
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

        // Extract the extension from the file name
        int index = fileName.lastIndexOf('.');
        if(index > 0){
            String extension = fileName.substring(index+1);
            return getCharset(extension);
        }

        return null;
    }
}