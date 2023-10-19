package ch.heig.dai.lab.fileio.dylanramos;

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
        try(var is = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))){
            var content = new StringBuilder();
            String line;

            while((line = is.readLine()) != null){
                content.append(line).append("\n");
            }

            return content.toString();
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
        try(var os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding))){
            os.write(content);

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
