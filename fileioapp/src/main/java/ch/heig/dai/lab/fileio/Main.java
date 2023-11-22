package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.Calum_Quinn.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String NEW_NAME = "Calum Quinn";

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
        System.out.println("Application started, reading folder " + folder);
        // TODO: implement the main method here

        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(NEW_NAME, wordsPerLine);

        File currentFile;

        while ((currentFile = fileExplorer.getNewFile()) != null) {
            try {
                // TODO: loop over all files

                // Don't reprocess files
                if (currentFile.getName().endsWith(".processed")) {
                    continue;
                }

                System.out.println("Begin file processing " + currentFile.getName());

                // Get charset
                Charset currentCharset = encodingSelector.getEncoding(currentFile);
                Objects.requireNonNull(currentCharset, "File extension unrecognised");

                // Read file
                String content = fileReaderWriter.readFile(currentFile, currentCharset);
                Objects.requireNonNull(content, "Unable to read content");

                // Transform content
                content = transformer.replaceChuck(content);
                content = transformer.capitalizeWords(content);
                content = transformer.wrapAndNumberLines(content);

                // Write result to file
                File result = new File(currentFile.getAbsolutePath() + ".processed");
                if (fileReaderWriter.writeFile(result, content, StandardCharsets.UTF_8)) {
                    throw new RuntimeException("Could not write to file");
                }
                System.out.println("File processed");

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
