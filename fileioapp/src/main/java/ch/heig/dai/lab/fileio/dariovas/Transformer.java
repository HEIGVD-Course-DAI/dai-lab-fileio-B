package ch.heig.dai.lab.fileio.dariovas;

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
        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        StringBuilder result = new StringBuilder();

        for(String word : source.split("\\s")){
            // Adds a space between all words except the first one
            if(!result.isEmpty())
                result.append(' ');

            // Capitalizes the first letter
            result.append(word.substring(0, 1).toUpperCase());

            // Adds the rest of the word
            if(word.length() > 1){
                result.append(word.substring(1));
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
        // Use the StringBuilder class to build the result string.
        StringBuilder result = new StringBuilder();
        String[] words = source.split("\\s");

        for(int i = 0; i < words.length; ++i){
            // Checks if it's a new line
            if(i % numWordsPerLine == 0){
                // Checks if it's an end of a line
                if(i / numWordsPerLine > 0){
                    result.append('\n');
                }
                // Adds the line number with a dot at the end
                result.append((i / numWordsPerLine + 1)).append('.');
            }

            result.append(' ').append(words[i]);
        }

        result.append('\n');

        return result.toString();
    }
}   