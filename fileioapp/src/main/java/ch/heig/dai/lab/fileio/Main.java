package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.romainfleury.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Romain Fleury";

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
        FileExplorer fe = new FileExplorer(folder);
        EncodingSelector es = new EncodingSelector();
        Transformer t = new Transformer(newName, wordsPerLine);
        FileReaderWriter frw = new FileReaderWriter();

        while (true) {
            try {
                // TODO: loop over all files
                File f = fe.getNewFile();
                if(f == null){
                    System.out.println("All Files have been processed.");
                    break;
                }

                Charset encoding = es.getEncoding(f);
                if(encoding != null) {
                    String newText = frw.readFile(f, encoding);
                    if (newText != null) {
                        newText = t.replaceChuck(newText);
                        newText = t.capitalizeWords(newText);
                        newText = t.wrapAndNumberLines(newText);

                        Path p = Paths.get(f.getPath() + ".processed");
                        frw.writeFile(p.toFile(), newText, Charset.forName("UTF-8"));
                        System.out.println("File processed : " + p.toFile());
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
