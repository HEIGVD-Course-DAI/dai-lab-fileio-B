package ch.heig.dai.lab.fileio.balkghar;

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
        String words[]=source.split("\\s");  
        String capitalizeWord="";
        for(String word:words){  
            String first=word.substring(0,1);  
            String afterfirst=word.substring(1);  
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";  
        }  
        return capitalizeWord.trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // Use the StringBuilder class to build the result string.
        
        // Use the StringBuilder class to build the result string.
        StringBuilder strTemp =  new StringBuilder();

        String words[]=source.split("\\s");

        for(int i = 0; i < words.length; ++i){
            if(i % numWordsPerLine == 0){
                if(i / numWordsPerLine > 0)
                    strTemp.append("\n");
                strTemp.append((i / numWordsPerLine + 1) + ".");
            }
            strTemp.append(" " + words[i]);
        }
        
        strTemp.append("\n");

        return strTemp.toString();
    }
}   