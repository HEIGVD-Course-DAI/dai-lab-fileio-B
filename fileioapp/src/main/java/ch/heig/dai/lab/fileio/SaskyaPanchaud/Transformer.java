package ch.heig.dai.lab.fileio.SaskyaPanchaud;

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
    public String replaceChuck(String source) { return source.replace("Chuck Norris", newName); }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        StringBuilder result = new StringBuilder();
        String[] words = source.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            result.append(word.substring(0, 1).toUpperCase() + word.substring(1));
            if (i != words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        StringBuilder result = new StringBuilder();
        String[] words = source.split(" ");

        int nbWord = 0;
        int nbLine = 1;

        while (nbWord < words.length) {
            result.append(nbLine++ + ".");
            for (int i = 0; i < numWordsPerLine && nbWord < words.length; i++, nbWord++) {
                result.append(" ");
                result.append(words[nbWord]);
            }
            result.append("\n");
        }

        return result.toString();
    }
}