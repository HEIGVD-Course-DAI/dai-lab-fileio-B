package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

// *** Import my own package ***
import ch.heig.dai.lab.fileio.julienbilleter.*;

public class Main {
    private static final String newName = "Julien Billeter";

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

        // Construct FileExplorer object with provided folder
        FileExplorer fileExplorer = new FileExplorer(folder);

        // Construct EncodingSelector and FileReaderWriter objects
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();

        // Construct Transformer object with newName and wordsPerLine
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                // --- Get a file from the file explorer and check it ---
                File file = fileExplorer.getNewFile();

                if (file == null) {
                    // No more files to process
                    System.out.println("All files have been processed.");
                    break;
                }

                // --- Get the file encoding and check it ---
                Charset encoding = encodingSelector.getEncoding(file);

                if (encoding == null) {
                    // Unsupported encodings and treated files (with extension .processed)
                    if (! (file.getName().lastIndexOf('.') != -1 &&
                           file.getName().substring(file.getName().lastIndexOf('.')).equals(".processed")) ) {
                        // unsupported encodings
                        System.out.println(Paths.get(file.getPath()) + " ignored");
                    }
                    continue;
                }

                // --- Read the file content and check it ---
                String content = fileReaderWriter.readFile(file, encoding);

                if (content != null) {
                    // Transform and write formatted content in a new file
                    content = transformer.wrapAndNumberLines( transformer.capitalizeWords( transformer.replaceChuck(content) ) );
                    Path pathFile = Paths.get(file.getPath() + ".processed");
                    fileReaderWriter.writeFile(pathFile.toFile(), content, StandardCharsets.UTF_8);

                    System.out.println(pathFile + " saved");
                } else {
                    System.out.println("/!\\ " + file.getPath() + " could not be read");
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
