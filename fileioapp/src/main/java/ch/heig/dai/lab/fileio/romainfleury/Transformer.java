package ch.heig.dai.lab.fileio.romainfleury;

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


        return source.replace("Chuck Norris",newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.

        String[] words = source.split(" ");
        String result = "";
        for(String w: words){
            result += w.substring(0,1).toUpperCase() + w.substring(1) + " ";
        }

        return result.substring(0,result.length() - 1);
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

        String[] words = source.split(" ");
        String result = "1. ";

        for(int i = 1; i <= words.length; i++){
            result += words[i-1];
            if(i % numWordsPerLine == 0){
                result += "\n" + ((i / numWordsPerLine) + 1) + ". ";
            }else{
                if(i != words.length){
                    result += " ";
                }else{
                    result += "\n";
                }
            }
        }

        return result;
    }
}   