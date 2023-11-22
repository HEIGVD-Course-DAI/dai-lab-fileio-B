package ch.heig.dai.lab.fileio.badisnt;

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
        String chuckNorris ="Chuck Norris";
        return source.replaceAll(chuckNorris, newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        for(int i=0;i<source.length();i++){
            if(i==0 || source.charAt(i-1)== ' '){
                source=source.substring(0, i)
                        + Character.toUpperCase(source.charAt(i))
                        + source.substring(i+1, source.length());
            }
        }
        return source;
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        if (source==null||source.length()==0||numWordsPerLine<1) return source;
        StringBuilder res = new StringBuilder();
        String words[] = source.split(" ");
        
        for(int i=0;i<words.length;i++){
            if (i % numWordsPerLine == 0){
                if (i>0) res.append("\n");
                res.append(String.valueOf(i / numWordsPerLine+1)+".");
            }
            res.append(" ");
            res.append(words[i]);
        }
        res.append("\n");
        return res.toString();
    }
}   