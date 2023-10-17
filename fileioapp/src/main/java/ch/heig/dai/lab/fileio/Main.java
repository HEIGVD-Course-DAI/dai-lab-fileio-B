package ch.heig.dai.lab.fileio;

import ch.heig.dai.lab.fileio.mystere.EncodingSelector;
import ch.heig.dai.lab.fileio.mystere.FileExplorer;
import ch.heig.dai.lab.fileio.mystere.FileReaderWriter;
import ch.heig.dai.lab.fileio.mystere.Transformer;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String newName = "Mystere";

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

        // Create necessary objects here (FileExplorer, EncodingSelector, FileReaderWriter, Transformer)
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        File inputFile;

        while ((inputFile = fileExplorer.getNewFile()) != null) {
            try {

                // Determine the encoding of the input file
                Charset encoding = encodingSelector.getEncoding(inputFile);

                if (encoding == null) {
                    System.out.println("Skip unknown encoding for file : " + inputFile.getName());
                    continue;
                }

                // Read the content of the input file
                String content = fileReaderWriter.readFile(inputFile, encoding);


                // Transform the content
                String transformedContent =
                        transformer.wrapAndNumberLines(
                                transformer.replaceChuck(
                                        transformer.capitalizeWords(content)));

                // Construct the output file name
                String outputFileName = inputFile.getName() + ".processed";

                // Write the transformed content to a new file
                fileReaderWriter.writeFile(
                        new File(inputFile.getParent(), outputFileName),
                        transformedContent, StandardCharsets.UTF_8);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
