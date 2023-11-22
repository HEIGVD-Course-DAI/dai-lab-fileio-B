package ch.heig.dai.lab.fileio.kalysso;

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
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            StringBuilder fileContent = new StringBuilder();
            String newLine = input.readLine();
            while (null != newLine) {
                fileContent.append(newLine).append("\n");
                newLine = input.readLine();
            }
            input.close();
            return fileContent.toString();
        } catch (Exception e) {
            return null;
        }
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
        try {
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
            output.write(content);
            output.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
