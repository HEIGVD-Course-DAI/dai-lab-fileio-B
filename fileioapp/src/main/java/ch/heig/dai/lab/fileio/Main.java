package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

import ch.heig.dai.lab.fileio.m4dh4t.*;

public class Main {
    private static final String newName = "m4dh4t";

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

        // Create the necessary objects
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                // Try to get a new file from the folder
                File file = fileExplorer.getNewFile();
                // Try to determine the encoding of the file
                Charset encoding = encodingSelector.getEncoding(file);
                // Try to read the file, skip it if an error occurred
                String content = fileReaderWriter.readFile(file, encoding);

                if (file == null) {
                    System.err.println("No more files to process, exiting...");
                    System.exit(0);
                } else if (content == null) {
                    System.err.println("Could not read " + file.getName() + ", skipping...");
                    continue;
                }

                // Transform the file content
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                // Write the result file
                String resultFileName = file.getName() + ".processed";
                File resultFile = new File(file.getParentFile(), resultFileName);
                boolean resultFileWriteStatus = fileReaderWriter.writeFile(
                    resultFile,
                    transformedContent,
                    encoding
                );

                // Output result file write status
                if (resultFileWriteStatus) {
                    System.out.println("File " + file.getName() + " processed successfully.");
                } else {
                    System.err.println("Error while writing file " + file.getName() + ".");
                }

                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
