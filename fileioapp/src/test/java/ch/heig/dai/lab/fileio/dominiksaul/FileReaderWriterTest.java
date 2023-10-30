package ch.heig.dai.lab.fileio.dominiksaul;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

class FileReaderWriterTest extends Assertions {
    private final static Charset CHARSET = StandardCharsets.UTF_8;
    private final static File FILE_THAT_DOES_NOT_EXIST = new File("/random/path");
    @TempDir
    private Path testDirectory;
    private final FileReaderWriter fileReaderWriter = new FileReaderWriter();

    @Test
    public void readFileThatDoesNotExistReturnNull() {
        assertNull(fileReaderWriter.readFile(FILE_THAT_DOES_NOT_EXIST, CHARSET));
    }

    @Test
    public void writeToFileThatDoesNotExistReturnFalse() {
        assertFalse(fileReaderWriter.writeFile(FILE_THAT_DOES_NOT_EXIST, "", CHARSET));
    }

    @Test
    public void writeAndReadFile() {
        File tmpFile = testDirectory.resolve("file.txt").toFile();
        String expectedFileContent = "Line1\nLine2\n";

        assertTrue(fileReaderWriter.writeFile(tmpFile, expectedFileContent, CHARSET));
        assertEquals(fileReaderWriter.readFile(tmpFile, CHARSET), expectedFileContent);
    }
}