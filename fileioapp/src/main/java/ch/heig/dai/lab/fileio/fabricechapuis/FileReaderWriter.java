package ch.heig.dai.lab.fileio.fabricechapuis;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        InputStream stream = new ByteArrayInputStream(file.getName().getBytes());
        InputStreamReader streamReader = new InputStreamReader(stream, encoding);
        BufferedReader buffReader = new BufferedReader(streamReader);

        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = buffReader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            return null;
        } finally {
            try {
                stream.close();
                streamReader.close();
                buffReader.close();
            } catch (IOException e) {
                return null;
            }
        }
        return sb.toString();
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
