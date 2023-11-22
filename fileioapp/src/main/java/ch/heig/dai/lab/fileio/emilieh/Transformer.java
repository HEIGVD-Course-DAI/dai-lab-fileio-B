package ch.heig.dai.lab.fileio.emilieh;

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
        // TODO: Implement the method body here.
        String chuckNorris = "Chuck Norris";
        source = source.replace(chuckNorris, newName);
        return source;
    }

    /**
     * Capitalize the first letter of each word in the string.
     *
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        String[] words = source.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        // Loop through all the words
        for (String word : words) {
            // Capitalize the first letter of the word
            String firstLetter = word.substring(0, 1).toUpperCase();
            // Add the first letter to the string builder
            stringBuilder.append(firstLetter);
            // Add the rest of the word to the string builder
            stringBuilder.append(word.substring(1));
            // Add a space to the string builder
            stringBuilder.append(" ");
        }
        // Remove the last space
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     *
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        if (source == null) {
            return null;
        }
        // TODO: Implement the method body here.
        // Use the StringBuilder class to build the result string.
        StringBuilder stringBuilder = new StringBuilder();
        // Split the string into words
        String[] words = source.split("\\s");
        // Loop through all the words
        for (int i = 0; i < words.length; i++) {
            // Add number of the line
            if (i % numWordsPerLine == 0) {
                stringBuilder.append((i / numWordsPerLine) + 1);
                stringBuilder.append(". ");
            }
            // Add word
            stringBuilder.append(words[i]);
            // Add a new line if it's the last word of the line
            if (i % numWordsPerLine == numWordsPerLine - 1 || i == words.length - 1) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(" ");
            }
        }


        return stringBuilder.toString();

    }
}   