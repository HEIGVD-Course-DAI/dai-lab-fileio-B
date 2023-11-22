package ch.heig.dai.lab.fileio.badisnt;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * 
     * @param file     the file to read.
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        // TODO: Implement the method body here.
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.
        //Streams and readers closed automatically at end of try block
        try (InputStream inputStream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(inputStream, encoding);
                BufferedReader buffReader = new BufferedReader(reader);) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = buffReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            return content.toString();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
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
        // TODO: Implement the method body here.
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to flush the data and close the streams and readers at the end.
        try (OutputStream outputStream = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream, encoding);
                BufferedWriter buffWriter = new BufferedWriter(writer)) {
            buffWriter.write(content);
            buffWriter.flush();
            return true;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return false;
        }
    }
}
