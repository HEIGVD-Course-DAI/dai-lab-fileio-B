package ch.heig.dai.lab.fileio;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.Istomine.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Alexandre Shyshmarov";

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

        var file = new FileExplorer(folder);
        var encode = new EncodingSelector();
        var fileRW = new FileReaderWriter();
        var transform = new Transformer(newName,wordsPerLine);

        while (true) {
            try {

                File input = file.getNewFile();

                if(input == null){System.out.println("No more file"); return;}

                Charset encoding = encode.getEncoding(input);

                if(encoding == null){continue;}

                String content = fileRW.readFile(input,encoding);

                content = transform.replaceChuck(content);
                content = transform.capitalizeWords(content);
                content = transform.wrapAndNumberLines(content);

                File output = new File(input.getAbsolutePath() + ".processed");

                fileRW.writeFile(output,content, StandardCharsets.UTF_8);

                
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
