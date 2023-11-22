package ch.heig.dai.lab.fileio.tomaspavoni;

import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        StringBuilder stringBuilderOutput = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file, encoding))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                stringBuilderOutput.append(line + '\n');
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        return stringBuilderOutput.toString();
    }

    /**
     * Write the content to a file with a given encoding. 
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        try(FileWriter writer = new FileWriter(file, encoding)) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return false;
        }
        return true;
    }
}
