package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

// mvn clean install --> dans le fichier pom
// java -jar target/fileioapp-1.0.jar jokes 5
// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.simeline.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Valentin Bugna";

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
        // TODO: implement the main method here

        var fileExplorer = new FileExplorer(folder);
        var encodingSelector = new EncodingSelector();
        var fileReaderWriter = new FileReaderWriter();

        while (true) {
            try {
                // TODO: loop over all files
                File inputFile = fileExplorer.getNewFile();

                if (inputFile == null) {
                    System.out.println("No more files in the folder. Exiting...");
                    break;
                }

                // Determine encoding
                Charset encoding = encodingSelector.getEncoding(inputFile);

                // Read the file
                String content = fileReaderWriter.readFile(inputFile, encoding);

                // Transform the content
                var transformer = new Transformer(newName, wordsPerLine);

                content = transformer.replaceChuck(content);
                // System.out.println(content);
                content = transformer.capitalizeWords(content);
                content = transformer.wrapAndNumberLines(content);

                // Write the result
                fileReaderWriter.writeFile(inputFile, content, encoding);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
