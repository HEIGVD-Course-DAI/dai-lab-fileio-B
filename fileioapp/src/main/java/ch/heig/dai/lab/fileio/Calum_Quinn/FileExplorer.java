package ch.heig.dai.lab.fileio.Calum_Quinn;

import java.io.File;
import java.util.HashSet;

public class FileExplorer {
    private final File folder;
    private final HashSet<File> knownFiles;

    /**
     * Constructor
     * Memorize the folder to explore and initialize the set of known files.
     * @param folder folder to be explored
     */
    public FileExplorer(String folder) {
        this.folder = new File(folder);
        this.knownFiles = new HashSet<>();
    }

    /**
     * Get a single new file from the folder.
     * The file must not have been returned before.
     * Use the java.io.file API to get the list of files in the folder.
     * Use the HashSet knownFiles to keep track of the files that have already been returned.
     * @return a new file, or null if there is no new file
     */
    public File getNewFile() {
        // TODO: implement the method body here
        File[] files = folder.listFiles();

        assert files != null;
        for (File file : files) {
            if (!knownFiles.contains(file)) {
                knownFiles.add(file);
                return file;
            }
        }

        return null;
    }
}