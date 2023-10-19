package ch.heig.dai.lab.fileio.Algorhythmn;

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
        return source.replaceAll("(Chuck Norris)", newName );
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        String[] wordList = source.split(" ", 0);
        for (int i = 0; i < wordList.length; i++) {
            wordList[i] = wordList[i].substring(0,1).toUpperCase() + wordList[i].substring(1);
        }
        return String.join(" ", wordList);
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] wordList = source.split(" ", 0);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.ceil((float) wordList.length / numWordsPerLine); i++) {
            result.append(String.valueOf(i + 1)).append(".");
            for (int j = 0; j < numWordsPerLine && i*numWordsPerLine + j < wordList.length ; j++) {
                result.append(" ").append(wordList[i * numWordsPerLine + j]);
            }
            result.append("\n");
        }
        return result.toString();
    }
}   