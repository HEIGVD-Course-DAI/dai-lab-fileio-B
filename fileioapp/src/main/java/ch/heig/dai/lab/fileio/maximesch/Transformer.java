package ch.heig.dai.lab.fileio.maximesch;

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
        StringBuilder result = new StringBuilder();
        String[] splitSource = source.split("\\s");

        for(int i = 0; i < splitSource.length; ++i){
            String curWord = splitSource[i];

            result.append(curWord.substring(0, 1).toUpperCase());
            if (curWord.length() > 1){
                result.append(curWord.substring(1, curWord.length()));
            }

            if(i != splitSource.length - 1){ // add a space before the next word (so if the current one is not the last)
                result.append(' ');
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
        StringBuilder result = new StringBuilder();
        String[] splitSource = source.split("\\s");

        int curWordNum = 0;
        int curLine = 1;

        while(curWordNum < splitSource.length){
            result.append(curLine);
            result.append('.');

            String curWord = splitSource[curWordNum];

            for(int i = 0; (i < numWordsPerLine) && (curWordNum < splitSource.length); ++i){
                curWord = splitSource[curWordNum];
                result.append(' ');
                result.append(curWord);
                ++curWordNum;
            }
            result.append('\n');
            ++curLine;
        }

        return result.toString();
    }
}   