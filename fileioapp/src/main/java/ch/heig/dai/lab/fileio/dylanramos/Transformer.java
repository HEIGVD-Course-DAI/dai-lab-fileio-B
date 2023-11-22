package ch.heig.dai.lab.fileio.dylanramos;

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
        String textToReplace = "Chuck Norris";

        return source.replaceAll(textToReplace, newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        source = source.substring(0, 0) + Character.toUpperCase(source.charAt(0)) + source.substring(1);

        for(int i = 1; i < source.length(); ++i) {
            if(source.charAt(i - 1) == ' ') {
                source = source.substring(0, i) + Character.toUpperCase(source.charAt(i)) + source.substring(i + 1);
            }
        }

        return source;
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        StringBuilder result = new StringBuilder(source.length());
        int wordsCount = 0;
        int index = 0;

        result.append("1. ");

        while(true) {
            // Get the word
            while (source.charAt(index) != ' ') {
                if (index == source.length() - 1) {
                    result.append(source.charAt(index)).append("\n");

                    return result.toString();
                }

                result.append(source.charAt(index));
                ++index;
            }

            ++wordsCount;

            // Add a new line if needed
            if (wordsCount % numWordsPerLine == 0) {
                result.append("\n").append(wordsCount / numWordsPerLine + 1).append(". ");
            } else {
                result.append(source.charAt(index));
            }

            ++index;
        }
    }
}   