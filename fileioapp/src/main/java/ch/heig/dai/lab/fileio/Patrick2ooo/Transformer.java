package ch.heig.dai.lab.fileio.Patrick2ooo;

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
        if(source == null){
            return source;
        }
        return source.replaceAll("Chuck Norris" , newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        String[] eachWord = source.split(" ");
        StringBuffer newString = new StringBuffer();

        for(String word : eachWord ){
            newString.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return newString.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // TODO: Implement the method body here.
        String[] eachWord = source.split("\\s");
        StringBuffer newString = new StringBuffer();

        int wordCounter = 0;
        int lineCounter = 1;

        for(String word : eachWord){
            if(wordCounter == 0){
                newString.append(lineCounter++).append(".");
            }

            newString.append(" ").append(word);
            wordCounter++;

            if(wordCounter == numWordsPerLine){
                newString.append("\n");
                wordCounter = 0;
            }
        }
        

        // Use the StringBuilder class to build the result string.
        newString.append("\n");
        return newString.toString();
    }
}   