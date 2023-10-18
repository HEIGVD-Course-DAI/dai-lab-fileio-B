package ch.heig.dai.lab.fileio.gitBugD;

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
        try{
            var reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), encoding));

            StringBuilder contentBuilder = new StringBuilder();
            String currentLine;

            while ((currentLine = reader.readLine()) != null)
            {
                contentBuilder.append(currentLine).append("\n");
            }

            reader.close();
            return contentBuilder.toString();
        }catch(Exception ex){
            ex.printStackTrace();
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
        try{
            file.createNewFile();
            var writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file), encoding));

            writer.write(content);
            writer.write("\r\n");

            writer.flush();
            writer.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
