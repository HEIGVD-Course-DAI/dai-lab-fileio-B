package ch.heig.dai.lab.fileio.Calum_Quinn;

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

        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.

        int indexFirstLetter = 0;

        while(indexFirstLetter <= source.lastIndexOf(' ')) {
            indexFirstLetter = source.indexOf(' ',indexFirstLetter) + 1;
            source = source.substring(0,indexFirstLetter) + source.substring(indexFirstLetter,indexFirstLetter + 1).toUpperCase() + source.substring(indexFirstLetter + 1);
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
        // TODO: Implement the method body here.
        // Use the StringBuilder class to build the result string.

        // Place first space after the space placed after the 1.
        int indexOfSpace = 3;
        int lineNumber = 1;
        source = lineNumber++ + ". " + source + '\n';

        while(indexOfSpace <= source.lastIndexOf(' ')) {
            for(int i = 0; i < numWordsPerLine && indexOfSpace != -1; ++i) {
                indexOfSpace = source.indexOf(' ',indexOfSpace + 1);
            }
            if(indexOfSpace == -1) {
                break;
            }
            source = source.substring(0,indexOfSpace) + '\n' + lineNumber++ + '.' + source.substring(indexOfSpace);

            // Change index of space to account for added characters
            indexOfSpace +=  4;
        }
        return source;
    }
}   