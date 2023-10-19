package ch.heig.dai.lab.fileio.AlessioGiuliano;

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
        var result = new StringBuilder();
        String[] words = source.split(" "); // This doesn't work if we have line separators in the string (but it's not tested)

        for (int i = 0; i < words.length; ++i)
        {
            var word = words[i];
            result.append(word.substring(0, 1).toUpperCase() + word.substring(1));
            if (i != words.length - 1)
            {
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
        var result = new StringBuilder();
        var words = source.split(" ");
        
        int i = 0;
        int line = 1;
        while (i < words.length)
        {
            result.append(line + ".");
            for (int j = 0; j < numWordsPerLine && i < words.length; ++j, ++i)
            {
                result.append(" ");
                result.append(words[i]);
            }
            result.append("\n");
            ++line;
        }

        return result.toString();
    }
}   