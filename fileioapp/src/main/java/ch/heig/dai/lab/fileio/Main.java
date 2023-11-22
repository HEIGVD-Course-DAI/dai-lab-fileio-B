package ch.heig.dai.lab.fileio;

import ch.heig.dai.lab.fileio.fabricechapuis.EncodingSelector;
import ch.heig.dai.lab.fileio.fabricechapuis.FileExplorer;
import ch.heig.dai.lab.fileio.fabricechapuis.FileReaderWriter;
import ch.heig.dai.lab.fileio.fabricechapuis.Transformer;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String newName = "Fabrice Chapuis";

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

        FileExplorer folderFiles = new FileExplorer(folder);
        EncodingSelector encoding = new EncodingSelector();
        FileReaderWriter readerWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);
        StringBuilder fileNameBuilder = new StringBuilder();

        while (true) {
            try {
                File file = folderFiles.getNewFile();
                if (file == null) {
                    System.out.println("No file left.");
                    break;
                }
                System.out.println("Reading file " + file.getName() + "...");

                Charset fileEncoding = encoding.getEncoding(file);
                if (fileEncoding == null) {
                    System.out.println("Encoding undefined or unknown.");;
                }
                String content = readerWriter.readFile(file, fileEncoding);

                if (content == null) {
                    System.out.println("Error while reading file or file " + file.getName() + " is empty.");
                    continue;
                }

                fileNameBuilder.append(file.getName()).append(".processed");
                String newContent = transformer.replaceChuck(content);
                newContent = transformer.capitalizeWords(newContent);
                newContent = transformer.wrapAndNumberLines(newContent);

                readerWriter.writeFile(new File(file.getParent(), fileNameBuilder.toString()), newContent, StandardCharsets.UTF_8);
                fileNameBuilder.setLength(0);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
