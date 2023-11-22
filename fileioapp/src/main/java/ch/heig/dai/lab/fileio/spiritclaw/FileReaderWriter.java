package ch.heig.dai.lab.fileio.spiritclaw;

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

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
            String line = "";
            StringBuilder fileContent = new StringBuilder();
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
            return fileContent.toString();

        } catch (IOException e) {
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
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding))) {
            br.write(content);
            return true;
        }
        catch(IOException e){
            return false;
        }
    }
}
