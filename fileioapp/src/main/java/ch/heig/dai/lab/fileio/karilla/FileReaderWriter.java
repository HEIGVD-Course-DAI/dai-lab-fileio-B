package ch.heig.dai.lab.fileio.karilla;

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

        StringBuilder data = new StringBuilder();
        String line;
        try(    InputStream in = new FileInputStream(file);
                Reader stream = new InputStreamReader(in, encoding);
                BufferedReader reader = new BufferedReader(stream)){

                while((line = reader.readLine()) != null){
                    data.append(line).append("\n");
                }
                reader.close();
                stream.close();
                in.close();
        }
        catch (Exception e){
            return null;
        }
        return data.toString();
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

        try(OutputStream out = new  FileOutputStream(file);
            Writer stream = new OutputStreamWriter(out, encoding);
            BufferedWriter writer = new BufferedWriter(stream)){

            writer.write(content);
            writer.close();
            stream.close();
            out.close();
        }
        catch (Exception e){
            return false;
        }


        return true;
    }
}
