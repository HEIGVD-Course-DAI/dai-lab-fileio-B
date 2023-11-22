package ch.heig.dai.lab.fileio.julienbilleter;

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
        // Assumption : Chuck Norris always in full and with this case
        //              Never Chuck alone or Norris alone
        return source.replace("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        if (source == null) {
            throw new NullPointerException("source is null !");
            // Exception would anyway be thrown by source.split(" ") below
        }

        // String[] words = source.split(" ");
        String[] words = source.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(word.substring(0, 1).toUpperCase())
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
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

        if (source == null) {
            throw new NullPointerException("source is null !");
            // Exception would anyway be thrown by source.split(" ") below
        }

        StringBuilder result = new StringBuilder();
        String[] words = source.split("\\s+"); // better than " " which is a special case of "\\s+"

        int lineNb = 1, wordNb = 0, wordTotalNb = 0;

        result.append(lineNb).append(". ");

        for (String word : words) {
            ++wordTotalNb;
            if (wordNb < numWordsPerLine) {
                result.append(word);
                if (wordNb != numWordsPerLine - 1 && wordTotalNb != words.length) { result.append(" "); }
                ++wordNb;
            } else {
                ++lineNb;
                result.append(System.lineSeparator()).append(lineNb).append(". ").append(word).append(" ");
                wordNb = 1;
            }
        }

        result.append(System.lineSeparator());

        return result.toString();
    }
}   