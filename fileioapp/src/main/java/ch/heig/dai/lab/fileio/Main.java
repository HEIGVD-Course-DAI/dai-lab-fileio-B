package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.amango.*;

public class Main {
    private static final String newName = "Aubry Mangold";
    private static final String outputSuffix = ".processed";
    private static final Charset outputEncoding = StandardCharsets.UTF_8;

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

        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                File inputFile = fileExplorer.getNewFile();
                // Check if there are any files left to process.
                if (inputFile == null) {
                    System.out.println("Done.");
                    break;
                } else if (inputFile.getName().endsWith(outputSuffix)) {
                    // Skip files that have already been processed.
                    continue;
                }

                Charset encoding = encodingSelector.getEncoding(inputFile);
                // Check whether the encoding is recognized, otherwise warn the user and skip the file.
                if (encoding == null) {
                    System.out.println("File " + inputFile.getName() + " has an unrecognized encoding, skipping...");
                    continue;
                }

                String content = fileReaderWriter.readFile(inputFile, encoding); // Already alerts the user on error.
                if (content == null) {
                    continue;
                }

                // Apply the desired transformations.
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                // Write the result to the output file.
                File outputFile = new File(inputFile.getAbsolutePath() + outputSuffix);
                fileReaderWriter.writeFile(outputFile, transformedContent, outputEncoding);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
