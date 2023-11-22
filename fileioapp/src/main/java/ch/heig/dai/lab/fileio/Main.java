package ch.heig.dai.lab.fileio;

import ch.heig.dai.lab.fileio.emilieh.Transformer;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Jean-Claude Van Damme";

    /**
     * Main method to transform files in a folder.
     * Create the necessary objects (FileExplorer, EncodingSelector, FileReaderWriter, Transformer).
     * In an infinite loop, get a new file from the FileExplorer, determine its encoding with the EncodingSelector,
     * read the file with the FileReaderWriter, transform the content with the Transformer, write the result with the
     * FileReaderWriter.
     * <p>
     * Result files are written in the same folder as the input files, and encoded with UTF8.
     * <p>
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
        // Create the necessary objects (FileExplorer, EncodingSelector, FileReaderWriter, Transformer).
        ch.heig.dai.lab.fileio.emilieh.FileExplorer fileExplorer = new ch.heig.dai.lab.fileio.emilieh.FileExplorer(folder);
        ch.heig.dai.lab.fileio.emilieh.EncodingSelector encodingSelector = new ch.heig.dai.lab.fileio.emilieh.EncodingSelector();
        ch.heig.dai.lab.fileio.emilieh.FileReaderWriter fileReaderWriter = new ch.heig.dai.lab.fileio.emilieh.FileReaderWriter();
        ch.heig.dai.lab.fileio.emilieh.Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                // In an infinite loop, get a new file from the FileExplorer, determine its encoding with the EncodingSelector,
                // TODO: loop over all files
                File currentFile = fileExplorer.getNewFile();
                if (currentFile == null) {
                    System.out.println("Finished");
                    break;
                } else {
                    System.out.println("Processing file " + currentFile.getName());
                }

                // read the file with the FileReaderWriter
                var charset = encodingSelector.getEncoding(currentFile);
                if (charset == null) {
                    continue;
                }
                String content = fileReaderWriter.readFile(currentFile, charset);

                //transform
                String transformedContent = transformer.wrapAndNumberLines(transformer.capitalizeWords(transformer.replaceChuck(content)));
                //write result
                fileReaderWriter.writeFile(new File(currentFile.getPath() + ".processed"), transformedContent, StandardCharsets.UTF_8);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
