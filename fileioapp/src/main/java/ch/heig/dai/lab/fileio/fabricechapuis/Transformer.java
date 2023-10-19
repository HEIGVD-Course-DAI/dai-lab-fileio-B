package ch.heig.dai.lab.fileio.fabricechapuis;

import static java.lang.Character.forDigit;
import static java.lang.Character.toUpperCase;

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
        String[] strWithoutName = source.split("Chuck Norris");
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < strWithoutName.length; ++i) {
            newString.append(strWithoutName[i]);
            if (i < strWithoutName.length - 1) {
                newString.append(this.newName);
            }
        }
        return newString.toString();
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        String[] words = source.split(" ");
        StringBuilder newWords = new StringBuilder();

        for (int i = 0; i < words.length; ++i) {
            newWords.append(toUpperCase(words[i].charAt(0)));
            for (int j = 1; j < words[i].length(); ++j) {
                newWords.append(words[i].charAt(j));
            }
            if (i < words.length - 1) {
                newWords.append(" ");
            }
        }
        return newWords.toString();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        int numLine = 0;
        StringBuilder result = new StringBuilder();

        String[] stringList = source.split(" ");

        for (int i = 0; i < stringList.length; ++i) {
            if (i != 0) {
                if (i % (numWordsPerLine) == 0) {
                    result.append("\n");
                }
                result.append(" ");
            }
            result.append(stringList[i]);
        }
        String[] linesList = result.toString().split("\n ");
        result.setLength(0);
        for (String str : linesList) {
            result.append((char) (++numLine + '0')).append(". ");

            result.append(str).append("\n");
        }
        return result.toString();
    }
}   