package ch.heig.dai.lab.fileio.amango;

import java.util.StringTokenizer;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;
    private static final String NAME_TO_REPLACE = "Chuck Norris";

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
        return source.replace(NAME_TO_REPLACE, newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        StringBuilder result = new StringBuilder();
        for (var word : source.split(" ")) {
            result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return result.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        StringTokenizer tokenizer = new StringTokenizer(source, " ");
        StringBuilder result = new StringBuilder();
        int lineIndex = 1;

        while (tokenizer.hasMoreElements()) {
            result.append(lineIndex++).append(". ");        // Append the line number.
            for (int i = 0; i < numWordsPerLine; i++) {     // Add numWordsPerLine tokens.
                if (tokenizer.hasMoreElements())
                    result.append(tokenizer.nextToken()).append(" ");
            }
            // Replace the last space with a system-dependant newline.
            result.replace(result.length() - 1, result.length(), System.lineSeparator());
        }

        return result.toString();
    }
}   