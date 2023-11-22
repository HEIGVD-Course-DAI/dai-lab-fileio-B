package ch.heig.dai.lab.fileio.fabricechapuis;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), encoding)) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            return null;
        }
        return content.toString();
    }

    /**
     * Write the content to a file with a given encoding. 
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file.getName()), encoding.displayName()));
            out.write(content);
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
