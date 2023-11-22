package ch.heig.dai.lab.fileio.LeoR;

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
        // TODO: Implement the method body here.

        if (source == null) {return null;}
        return source.replace("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.

        if (source == null) return null;

        String[] words = source.split("\\s");
        StringBuilder capitalizedWords = new StringBuilder();

        for (String word : words)
        {
            if (word.length() > 0)
            {
                capitalizedWords
                    .append(Character.toUpperCase(word.charAt(0))) // First letter to uppercase
                    .append(word.substring(1).toLowerCase()) // Rest of the word as lowercase
                    .append(" ");}
        }

        return capitalizedWords.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // TODO: Implement the method body here.
        // Use the StringBuilder class to build the result string.

        if (source == null || source.isEmpty()) {return source;}

        StringBuilder result = new StringBuilder();
        String[] words = source.split(" ");

        int currentLineWordCount = 0;
        int lineNumber = 1;

        // Iterating on every word
        for (int i = 0; i < words.length; i++)
        {
            // If the line is empty, it's still a line to count
            if (currentLineWordCount == 0)
            {
                result.append(lineNumber).append(". ");
                lineNumber++;
            }

            result.append(words[i]);
            currentLineWordCount++;

            // Break the line if the max number of words is reached
            if (currentLineWordCount == numWordsPerLine)
            {
                result.append("\n");
                currentLineWordCount = 0;
            }
            else
            {
                result.append(" ");
            }
        }

        // Removing the last space
        if (result.charAt(result.length() - 1) == ' ')
        {
            result.deleteCharAt(result.length() - 1);
        }

        // Ensure there is a line break at the end
        result.append("\n");

        return result.toString();
    }
}   