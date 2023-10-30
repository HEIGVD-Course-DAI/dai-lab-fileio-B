package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.dominiksaul.*;

public class Main {
    private static final String newName = "Dominik Saul";
    private static final String outputFileSuffix = ".processed";
    private static final Charset outputCharset = StandardCharsets.UTF_8;

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

        var fileExplorer = new FileExplorer(args[0]);
        var encodingSelector = new EncodingSelector();
        var fileReaderWriter = new FileReaderWriter();
        var transformer = new Transformer(newName, Integer.parseInt(args[1]));

        while (true) {
            try {
                // Get a new file in the directory, exit the while if there is no new file
                File file = fileExplorer.getNewFile();
                if (file == null) break;
                // Skip to next file if the file is an output file
                if (file.getName().contains(outputFileSuffix)) continue;

                // Get the encoding of the file, throw an exception if it can't be determent
                Charset charset = encodingSelector.getEncoding(file);
                if (charset == null) throw new Exception("Failed to detect encoding of file: " + file.getName());

                // Read the content of the file, throw an error if it fails
                String text = fileReaderWriter.readFile(file, charset);
                if (text == null) throw new Exception("Failed to read file: " + file.getName());

                // Transform text
                text = transformer.replaceChuck(text);
                text = transformer.capitalizeWords(text);
                text = transformer.wrapAndNumberLines(text);

                // Create output file and write the text into it, throw an error if it fails
                var outputFile = new File(args[0] + "/" + file.getName() + outputFileSuffix);
                if (!fileReaderWriter.writeFile(outputFile, text, outputCharset)) {
                    throw new Exception("Failed to write file: " + outputFile.getName());
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
