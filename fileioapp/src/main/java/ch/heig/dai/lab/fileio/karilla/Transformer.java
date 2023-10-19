package ch.heig.dai.lab.fileio.karilla;

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
    public String replaceChuck(String source){

        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        String[] wordList = source.split(" ");
        StringBuilder newStringBuilder = new StringBuilder();
        String capitalizedLetter = "";
        for (String s : wordList) {
            capitalizedLetter = Character.toString(Character.toUpperCase(s.charAt(0)));
            newStringBuilder.append(capitalizedLetter).append(s.substring(1)).append(" ");
        }
        String newString = newStringBuilder.toString();
        newString = newString.substring(0,newString.length() -1);
        return newString;
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