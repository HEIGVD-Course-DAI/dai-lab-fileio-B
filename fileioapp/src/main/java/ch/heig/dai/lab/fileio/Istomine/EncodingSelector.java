package ch.heig.dai.lab.fileio.Istomine;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.*;
import java.util.*;

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
    private Map<String,Charset> charMap = Map.of(
            "utf8",StandardCharsets.UTF_8,
            "txt",StandardCharsets.US_ASCII,
            "utf16be",StandardCharsets.UTF_16BE,
            "utf16le",StandardCharsets.UTF_16LE
    );
    public Charset getEncoding(File file){

        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");

        if (lastIndexOf <= 0) {
            return null;
        }

        return charMap.get(name.substring(lastIndexOf + 1));
    }
}