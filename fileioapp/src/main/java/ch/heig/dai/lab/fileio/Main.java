package ch.heig.dai.lab.fileio;

import java.io.File;

import ch.heig.dai.lab.fileio.AlessioGiuliano.*;

public class Main {
    private static final String newName = "Alessio Giuliano";

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
        var explorer = new FileExplorer(folder);

        while (true) {
            try {
                File file = explorer.getNewFile();
                if (file == null)
                {
                    break;
                }
                var encodingSelector = new EncodingSelector();
                var encoding = encodingSelector.getEncoding(file);

                if (encoding == null)
                {
                    continue;
                }

                var readerWriter = new FileReaderWriter();
                String content = readerWriter.readFile(file, encoding);

                var transformer = new Transformer(newName, wordsPerLine);
                content = transformer.replaceChuck(content);
                content = transformer.wrapAndNumberLines(content);
                content = transformer.capitalizeWords(content);

                var outputFile = new File(file.getPath() + ".processed");
                readerWriter.writeFile(outputFile, content, encoding);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
