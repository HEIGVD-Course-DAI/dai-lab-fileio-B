package ch.heig.dai.lab.fileio.Calum_Quinn;

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
        // TODO: Implement the method body here. 
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.

        BufferedReader reader = null;
        int count;
        String content = "";
        try {
            reader = new BufferedReader(new FileReader(file, encoding));
            while((count = reader.read()) != -1) {
                content += reader.read();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
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

        BufferedWriter writer = null;
        BufferedReader reader = null;
        int count;
        try {
            reader = new BufferedReader(new FileReader(file,encoding));
            while((count = reader.read()) != -1) {
                writer.write(content);
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
