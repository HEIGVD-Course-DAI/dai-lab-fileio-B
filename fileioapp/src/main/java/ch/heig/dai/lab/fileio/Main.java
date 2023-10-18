package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


import ch.heig.dai.lab.fileio.dariovas.*;

public class Main {
    private static final String newName = "Dario Vasques";
    private static final Charset OUTPUT_CHARSET = StandardCharsets.UTF_8;
    private static final String SUFFIXE = ".processed";
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

        EncodingSelector encoding = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        FileExplorer file = new FileExplorer(folder);
        Transformer transformer = new Transformer(newName, wordsPerLine);

        File srcFile;

        while ((srcFile = file.getNewFile()) != null) {
            try {
                if(srcFile.getName().endsWith(SUFFIXE)){
                    System.out.println("Already processed");
                    continue;
                }

                System.out.println("Starting to process file " + srcFile);

                // Gets source file charset
                Charset srcFileCharset = encoding.getEncoding(srcFile);
                Objects.requireNonNull(srcFileCharset, "File extension unrecognised");

                // Reads file content
                String content = fileReaderWriter.readFile(srcFile, srcFileCharset);
                Objects.requireNonNull(content, "Unable to read content");

                // Modify file content
                content = transformer.replaceChuck(content);
                content = transformer.capitalizeWords(content);
                content = transformer.wrapAndNumberLines(content);

                // Creates the result file
                File result = new File(srcFile.getAbsolutePath() + SUFFIXE);

                // Writes the result file
                if(!fileReaderWriter.writeFile(result, content, OUTPUT_CHARSET)){
                    throw new RuntimeException("Could not write to file");
                }

                System.out.println("File processed");

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
