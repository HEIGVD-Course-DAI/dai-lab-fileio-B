package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.jehrensb.*;
import ch.heig.dai.lab.fileio.romainfleury.FileReaderWriter;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Jean-Claude Van Damme";

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
        ch.heig.dai.lab.fileio.romainfleury.FileExplorer fe = new ch.heig.dai.lab.fileio.romainfleury.FileExplorer(args[1]);
        ch.heig.dai.lab.fileio.romainfleury.EncodingSelector es = new ch.heig.dai.lab.fileio.romainfleury.EncodingSelector();
        ch.heig.dai.lab.fileio.romainfleury.Transformer t = new ch.heig.dai.lab.fileio.romainfleury.Transformer(newName, 3);
        ch.heig.dai.lab.fileio.romainfleury.FileReaderWriter frw = new FileReaderWriter();

        while (true) {
            try {
                // TODO: loop over all files
                File f = fe.getNewFile();
                Charset encoding = es.getEncoding(f);
                String newText = frw.readFile(f, encoding);
                newText = t.replaceChuck(newText);
                newText = t.capitalizeWords(newText);
                newText = t.wrapAndNumberLines(newText);

                File fw = new File(f.getName() + ".processed");

                frw.writeFile(fw, newText, Charset.forName("UTF-8"));

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
