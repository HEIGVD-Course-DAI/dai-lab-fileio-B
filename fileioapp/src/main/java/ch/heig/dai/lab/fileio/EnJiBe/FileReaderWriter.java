package ch.heig.dai.lab.fileio.EnJiBe;

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
    try (   Reader reader = new FileReader(file, encoding);
            BufferedReader bufferedReader = new BufferedReader(reader))
        {
            StringBuilder content = new StringBuilder();
            String line;
            
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line).append("\n");
            }
            return content.toString();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    /**
     * Write the content to a file with a given encoding.
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        try (   Writer writer = new FileWriter(file, encoding);
                BufferedWriter bufferedWriter = new BufferedWriter(writer))
        {
            bufferedWriter.write(content);
            bufferedWriter.flush();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}
