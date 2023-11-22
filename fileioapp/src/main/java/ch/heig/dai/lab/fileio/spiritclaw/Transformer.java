package ch.heig.dai.lab.fileio.spiritclaw;

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
        return source.replaceAll("Chuck\\sNorris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // Separate words by spaces
        String[] words = source.split("\\s");

        // If there are no words return the source
        if(words.length == 0){
            return source;
        }

        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if(!word.isEmpty()){
                // Add the first char in upper case
                result.append(Character.toUpperCase(word.charAt(0)));
                // If there are more letters/chars in the word, add them
                if(word.length() > 1) {
                    result.append(word.substring(1));
                }
                // Add a space as this is the end of the word
                result.append(" ");
            }
        }
        // Return the new value but without the trailing space
        return result.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // Separate words by spaces
        String[] words = source.split("\\s");

        // If there are no words return the source
        if(words.length == 0){
            return source;
        }

        StringBuilder builder = new StringBuilder();
        // Will be used to count the word per line
        int wordCounter = 0;

        // Will be used to number the lines
        int lineCounter = 1;
        for (String word : words) {
            // If start if a line add the line number
            if(wordCounter == 0){
                builder.append(lineCounter).append(".");
                ++lineCounter;
            }
            // Add a leading space and the word
            builder.append(" ");
            builder.append(word);

            // Increment the word
            ++wordCounter;

            // If we reached the end of the line, add the line separator
            if(wordCounter == numWordsPerLine){
                builder.append(System.lineSeparator());
                wordCounter = 0;
            }
        }
        // Add a last line separator at the end
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}   