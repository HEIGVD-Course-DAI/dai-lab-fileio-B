package ch.heig.dai.lab.fileio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.Patrick2ooo.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Jean-Claude Van Damme";

    /**
     * Main method to transform files in a folder.
     * Create the necessary objects (FileExplorer, EncodingSelector, FileReaderWriter, Transformer).
     * In an infinite loop, get a new file from the FileExplorer, determine its encoding with the EncodingSelector,
     * read the file with the FileReaderWriter, transform the content with the Transformer, write the result with the
     * FileReaderWriter.
     * 
     * Result files are written in the same folder as the input files, and encoded with UTF8.
     *
     * File name of the result file:
     * an input file "myfile.utf16le" will be written as "myfile.utf16le.processed",
     * i.e., with a suffixe ".processed".
     */
    public static void main(String[] args) {
        // Read command line arguments
        if (args.length != 2 || !new File(args[0]).isDirectory()) {
            System.out.println("You need to provide two command line arguments: an existing folder and the number of words per line.");
            System.exit(1);
        }
        String folder = args[0];
        int wordsPerLine = Integer.parseInt(args[1]);
        System.out.println("Application started, reading folder " + folder + "...");
        // mvn package
        // java -jar target/fileioapp-1.0.jar /home/pmaillard/Desktop/jokes/ 5
        // TODO: implement the main method here

        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        File actualFile;

        while ((actualFile = fileExplorer.getNewFile()) != null) {
            try {
                // TODO: loop over all files
                if(actualFile.getName().endsWith(".New")){
                    continue;
                }

                Charset actualCharset = encodingSelector.getEncoding(actualFile);

                String content = fileReaderWriter.readFile(actualFile, actualCharset);

                content = transformer.replaceChuck(content);
                content = transformer.capitalizeWords(content);
                content = transformer.wrapAndNumberLines(content);

                File outputFile = new File(actualFile.getPath() + ".New"); // probleme ici
                if(!fileReaderWriter.writeFile(outputFile, content, actualCharset)){
                    throw new IOException("file couldn't be written");
                }

                System.out.println("New File created");

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        System.out.println("Process finished");
    }    
}
