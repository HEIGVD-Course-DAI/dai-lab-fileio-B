package ch.heig.dai.lab.fileio.EnJiBe;

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
        return source.replace("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        if (source == null || source.isEmpty())
        {
            return source;
        }

        String[] words = source.split("\\s+");
        StringBuilder capitalizedString = new StringBuilder();

        for (String word : words)
        {
            if (!word.isEmpty())
            {
                capitalizedString.append(Character.toUpperCase(word.charAt(0)));
                capitalizedString.append(word.substring(1).toLowerCase());
                capitalizedString.append(" ");
            }
        }

        return capitalizedString.toString().trim();

    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        if (source == null || source.isEmpty())
        {
            return source;
        }

        String[] words = source.split("\\s+");
        StringBuilder transformedString = new StringBuilder();
        int wordCount = 0;
        int lineNumber = 1;

        for (String word : words)
        {
            if (wordCount == numWordsPerLine)
            {
                transformedString.deleteCharAt(transformedString.length() - 1);
                transformedString.append("\n");
                wordCount = 0;
            }

            if (0 == wordCount)
            {
                transformedString.append(lineNumber++).append(". ");
            }

            transformedString.append(word).append(" ");
            ++wordCount;
        }

        return transformedString.deleteCharAt(transformedString.length() - 1).append('\n').toString();
    }
}

