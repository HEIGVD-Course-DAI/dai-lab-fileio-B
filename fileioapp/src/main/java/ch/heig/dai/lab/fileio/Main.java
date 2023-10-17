package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.kalysso.*;

public class Main {
    private static final String newName = "Kalysso";

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
                // Get the file
                File file = fileExplorer.getNewFile();
                if(null == file)
                {
                    System.out.println("No more files. End of processing.");
                    break;
                }

                String filename = file.getName();

                // Detect the encoding
                Charset encoding = encodingSelector.getEncoding(file);
                if(null == encoding)
                {
                    System.out.println("Unable to detect encoding of file '" + filename + "'.");
                    continue;
                }

                // Read the file
                String fileContent = fileReaderWriter.readFile(file, encoding);
                if(null == fileContent)
                {
                    System.out.println("Unable to read the content of file '" + filename + "'.");
                    continue;
                }

                System.out.println("Transforming file '" + filename + "'");

                // Transform the file content
                fileContent = transformer.replaceChuck(fileContent);
                fileContent = transformer.capitalizeWords(fileContent);
                fileContent = transformer.wrapAndNumberLines(fileContent);

                // Write it back in the source folder, with the new encoding
                fileReaderWriter.writeFile(new File(file.getParent(), filename + ".processed"), fileContent, StandardCharsets.UTF_8);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
