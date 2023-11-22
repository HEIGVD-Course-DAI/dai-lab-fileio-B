package ch.heig.dai.lab.fileio.kalysso;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;

    /**
     * Constructor
     * Initialize the Transformer with the name to replace "Chuck Norris" with
     * and the number of words per line to use when wrapping the text.
     *
     * @param newName         the name to replace "Chuck Norris" with
     * @param numWordsPerLine the number of words per line to use when wrapping the text
     */
    public Transformer(String newName, int numWordsPerLine) {
        this.newName = newName;
        this.numWordsPerLine = numWordsPerLine;
    }

    /**
     * Replace all occurrences of "Chuck Norris" with the name given in the constructor.
     *
     * @param source the string to transform
     * @return the transformed string
     */
    public String replaceChuck(String source) {
        return (null == source ? null : source.replaceAll("Chuck Norris", newName));
    }

    /**
     * Capitalize the first letter of each word in the string.
     *
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        if (null == source || source.isEmpty())
            return source;

        StringBuilder capitalizedSource = new StringBuilder();
        for (String word : source.split("\\s")) {
            if (!capitalizedSource.isEmpty())
                capitalizedSource.append(" ");
            capitalizedSource.append(word.substring(0, 1).toUpperCase());
            if (word.length() > 1)
                capitalizedSource.append(word.substring(1));
        }
        return capitalizedSource.toString();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     *
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        if (null == source)
            return source;

        int wordCounter = 0;
        int lineCounter = 1;
        StringBuilder wrappedSource = new StringBuilder();
        for (String word : source.split("\\s")) {
            // Add the line number before the first word
            if (wordCounter == 0)
                wrappedSource.append(lineCounter).append(".");

            // Append the next word
            wrappedSource.append(" ").append(word);
            wordCounter++;

            if (wordCounter == numWordsPerLine) {
                wrappedSource.append("\n");
                wordCounter = 0;
                lineCounter++;
            }
        }
        return wrappedSource.append("\n").toString();
    }
}   