package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import ch.heig.dai.lab.fileio.maximesch.*;

public class Main {
    private static final String newName = "Maxime Schaller";

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

        EncodingSelector encodingSelector = new EncodingSelector();
        FileExplorer fileExplorer = new FileExplorer(folder);

        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer("Maxime Schaller", wordsPerLine);

        File sourceFile;


        while (fileExplorer.getNewFile() != null) {
            try {
                sourceFile = fileExplorer.getNewFile();
                String sourceFilePath = sourceFile.getAbsolutePath();
                // TODO: loop over all files

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }

        System.out.println("Application ended, read ");
    }
}
