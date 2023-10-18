package ch.heig.dai.lab.fileio.tomaspavoni;

import javax.swing.*;
import java.util.Arrays;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;

    /**
     * Constructor
     * Initialize the Transformer with the name to replace "Chuck Norris" with 
     * and the number of words per line to use when wrapping the text.
     * @param newName the name to replace "Chuck Norris" with
     * @param numWordsPerLine the number of words per line to use when wrapping the text
     */
    public Transformer(String newName, int numWordsPerLine) {
        this.newName = newName;
        this.numWordsPerLine = numWordsPerLine;
    }

    /**
     * Replace all occurrences of "Chuck Norris" with the name given in the constructor.
     * @param source the string to transform
     * @return the transformed string
     */
    public String replaceChuck(String source) {
        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        if (source == null || source.isEmpty()) {
            return "";
        }

        String[] words = source.split("\\s");
        StringBuilder captitalizeWords = new StringBuilder();
        for (int i = 0; i < words.length; ++i) {
            String firstLetter = words[i].substring(0,1);
            String secondLetter = words[i].substring(1);
            captitalizeWords.append(firstLetter.toUpperCase()).append(secondLetter);
            if (i < words.length - 1) {
                captitalizeWords.append(' ');
            }
        }

        return captitalizeWords.toString();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        if (source == null || source.isEmpty() || numWordsPerLine <= 0) {
            return "";
        }

        String[] words = source.split("\\s+");

        StringBuilder wrappedLines = new StringBuilder();
        int lineCount = 0;
        int wordCount = 0;
        while (wordCount < words.length) {
            wrappedLines.append(++lineCount).append(". ");
            for (int j = 0; j < numWordsPerLine; ++j) {
                wrappedLines.append(words[wordCount++]);
                if (wordCount == words.length) break;
                if (j < numWordsPerLine - 1) {
                    wrappedLines.append(' ');
                }
            }
            wrappedLines.append('\n');
        }

        return wrappedLines.toString();
    }
}   