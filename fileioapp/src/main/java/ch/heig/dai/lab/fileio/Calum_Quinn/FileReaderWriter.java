package ch.heig.dai.lab.fileio.Calum_Quinn;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding text encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        // TODO: Implement the method body here. 
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.

        BufferedReader reader;
        String content;
        try {
            reader = new BufferedReader(new FileReader(file, encoding));
            content = reader.readLine();

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;

    }

    /**
     * Write the content to a file with a given encoding.
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        // TODO: Implement the method body here.
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to flush the data and close the streams and readers at the end.

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file,encoding));
            writer.write(content);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}