package ch.heig.dai.lab.fileio.emariaux;

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
        String words[]=source.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
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

        String[] words = source.split(" ");
        StringBuilder result = new StringBuilder();
        int lineCount = 1;
        int wordsInLine = 0;

        for (String word : words) {
            if (wordsInLine == 0) {
                result.append(lineCount).append(". ");
                wordsInLine++;
            } else if (wordsInLine >= numWordsPerLine) {
                result.append(" ");
                result.append(word);
                result.append("\n");
                lineCount++;
                wordsInLine = 0;
                continue;
            } else {
                result.append(" ");
            }
            result.append(word);
            wordsInLine++;
        }
        result.append("\n");
        return result.toString();
    }


}   