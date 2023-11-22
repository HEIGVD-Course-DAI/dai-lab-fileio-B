package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.EnJiBe.*;

public class Main {
    private static final String NEW_NAME = "Nicolas Junod";
    private static final String PROCESSED_SUFFIX = ".processed";
    private static final Charset PROCESSED_ENCODING = StandardCharsets.UTF_8;

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
        Transformer transformer = new Transformer(NEW_NAME, wordsPerLine);
        FileReaderWriter fileReaderWriter = new FileReaderWriter();

        File sourceFile;
        while ((sourceFile = fileExplorer.getNewFile()) != null) {
            try {
                // retrieve file encoding
                Charset encoding = encodingSelector.getEncoding(sourceFile);

                // skip unknown encoding (.processed being "unknown")
                if (encoding == null) continue;

                System.out.println(sourceFile.getName());

                // reading file content
                String content = fileReaderWriter.readFile(sourceFile, encoding);
                if (content == null)
                {
                    System.out.println("Could not read file content");
                    continue;
                }

                // transforming file content
                content = transformer.replaceChuck(content);
                content = transformer.capitalizeWords(content);
                content = transformer.wrapAndNumberLines(content);

                // writing file content
                File newFile = new File(sourceFile.getAbsolutePath() + PROCESSED_SUFFIX);
                if (!fileReaderWriter.writeFile(newFile, content, PROCESSED_ENCODING))
                {
                    System.out.println("Could not write modified content");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
