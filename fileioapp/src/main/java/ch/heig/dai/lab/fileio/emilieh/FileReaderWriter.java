package ch.heig.dai.lab.fileio.emilieh;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     *
     * @param file     the file to read.
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.
        StringBuilder s = new StringBuilder();
        try (var stream = new FileInputStream(file);
             var reader = new InputStreamReader(stream, encoding)) {
            int c;
            while ((c = reader.read()) != -1) {
                s.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("Error while reading file");
        }
        return s.toString();
    }

    /**
     * Write the content to a file with a given encoding.
     *
     * @param file     the file to write to
     * @param content  the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        // TODO: Implement the method body here. 
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to flush the data and close the streams and readers at the end.
        try (
                var stream = new FileOutputStream(file);
                var writer = new OutputStreamWriter(stream, encoding)
        ) {
            writer.write(content);
            writer.flush();
            writer.close();
            stream.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error while writing file");
            return false;

        }
    }
}
