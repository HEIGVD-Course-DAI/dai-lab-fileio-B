package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.SaskyaPanchaud.EncodingSelector;
import ch.heig.dai.lab.fileio.SaskyaPanchaud.FileExplorer;
import ch.heig.dai.lab.fileio.SaskyaPanchaud.FileReaderWriter;
import ch.heig.dai.lab.fileio.SaskyaPanchaud.Transformer;

public class Main {
    private static final String newName = "Saskya Panchaud";

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

        var fileExplorer = new FileExplorer(folder);
        var encodingSelector = new EncodingSelector();
        var fileReaderWriter = new FileReaderWriter();
        var transformer = new Transformer(newName, wordsPerLine);

        File currentFile;

        while (true) {
            int i = 0;
            try {
                // get file
                currentFile = fileExplorer.getNewFile();
                if (currentFile == null) {
                    break;
                }
                if (currentFile.getName().endsWith(".processed")) {
                    continue;
                }

                // get charset
                Charset currentCharset = encodingSelector.getEncoding(currentFile);

                // read file
                String currentContent = fileReaderWriter.readFile(currentFile, currentCharset);

                // transform content
                currentContent = transformer.replaceChuck(currentContent);
                currentContent = transformer.capitalizeWords(currentContent);
                currentContent = transformer.wrapAndNumberLines(currentContent);

                // write in file
                File transformedFile = new File(currentFile.getAbsolutePath() + ".processed");
                boolean writeOK = fileReaderWriter.writeFile(transformedFile, currentContent, StandardCharsets.UTF_8);
                if (!writeOK) {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
