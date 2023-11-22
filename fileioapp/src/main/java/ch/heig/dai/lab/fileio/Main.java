package ch.heig.dai.lab.fileio;

import ch.heig.dai.lab.fileio.ansermgw.EncodingSelector;
import ch.heig.dai.lab.fileio.ansermgw.FileExplorer;
import ch.heig.dai.lab.fileio.ansermgw.FileReaderWriter;
import ch.heig.dai.lab.fileio.ansermgw.Transformer;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main {
    private static final String NEW_NAME = "Gwenael Ansermoz";
    private static final String PROCESSED_FILE_EXTENSION = ".processed";
    private static final Charset PROCESSED_FILE_CHARSET = StandardCharsets.UTF_8;

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

        FileExplorer fileExplorer = new FileExplorer(folder);
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        EncodingSelector encodingSelector = new EncodingSelector();
        Transformer transformer = new Transformer(NEW_NAME, wordsPerLine);

        File sourceFile;
        while ((sourceFile = fileExplorer.getNewFile()) != null) {
            try {
                String sourceFilePath = sourceFile.getAbsolutePath();

                // avoid processing result file
                if (sourceFilePath.endsWith(PROCESSED_FILE_EXTENSION)) {
                    continue;
                }

                System.out.println("Starting to process file " + sourceFilePath);

                // retrieve file charset
                Charset sourceFileCharset = encodingSelector.getEncoding(sourceFile);
                Objects.requireNonNull(sourceFileCharset, "Unrecognized file extension detected");
                System.out.println("Detected charset: " + sourceFileCharset.name());

                // read file content
                String sourceFileContent = fileReaderWriter.readFile(sourceFile, sourceFileCharset);
                Objects.requireNonNull(sourceFileContent, "Could not read file content");
                System.out.println("Content length: " + sourceFileContent.length());

                // transform file content
                sourceFileContent = transformer.replaceChuck(sourceFileContent);
                sourceFileContent = transformer.capitalizeWords(sourceFileContent);
                sourceFileContent = transformer.wrapAndNumberLines(sourceFileContent);

                // save transformed content to output file
                File outputFile = new File(sourceFile.getAbsolutePath() + PROCESSED_FILE_EXTENSION);
                boolean isWriteSuccessful = fileReaderWriter.writeFile(
                        outputFile,
                        sourceFileContent,
                        PROCESSED_FILE_CHARSET
                );

                if (!isWriteSuccessful) {
                    throw new RuntimeException("Could not write transformed content back to file");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
