package ch.heig.dai.lab.fileio.gitBugD;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;

    public static void main(String[] args) {
        Transformer t = new Transformer("ciao", 2);
        t.wrapAndNumberLines("Example of a very long sentence blabla.");
    }

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
        final String toReplace = "Chuck Norris";

        return source.replaceAll(toReplace, this.newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        final String delimiter = " ";
        String[] splitSource = source.split(delimiter);
        int lengthSentence = splitSource.length;
        String[] splitCapitalized = new String[lengthSentence];

        for(int i = 0; i < lengthSentence; ++i){
            String word = splitSource[i];
            StringBuilder result = new StringBuilder();
            result.append(Character.toUpperCase(word.charAt(0)));
            result.append(word.substring(1));
            splitCapitalized[i] = String.valueOf(result);
        }

        return String.join(delimiter, splitCapitalized);
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        StringBuilder result = new StringBuilder();
        int lengthSentence = source.length();
        int lineNumber = 1;
        int numWords = 0;
        int lineStart = 0;

        for(int lineEnd = 0; lineEnd < lengthSentence; ++lineEnd){
            if(lineEnd + 1 == lengthSentence){
                result.append(lineNumber).append(". ");
                result.append(source, lineStart, lineEnd + 1);
                result.append("\n");
            }
            else if(source.charAt(lineEnd) == ' '){
                ++numWords;
                if(numWords == numWordsPerLine){
                    result.append(lineNumber).append(". ");
                    result.append(source, lineStart, lineEnd);
                    result.append("\n");

                    ++lineNumber;
                    numWords = 0;
                    lineStart = lineEnd + 1;
                }
            }
        }
        return String.valueOf(result);
    }
}   