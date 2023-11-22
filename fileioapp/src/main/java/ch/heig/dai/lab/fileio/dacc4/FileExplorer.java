package ch.heig.dai.lab.fileio.dacc4;

import java.io.File;
import java.util.HashSet;

public class FileExplorer {
    private final File folder;
    private final HashSet<File> knownFiles;

    /**
     * Constructor
     * Memorize the folder to explore and initialize the set of known files.
     * @param folder
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
        File[] files = folder.listFiles();

        // If the folder is empty or all files have been returned, return null
        if (files == null || files.length == knownFiles.size()) {
            return null;
        }

        // Find the first file that hasn't been returned yet
        File newFile = null;
        for (File file : files) {
            if (!knownFiles.contains(file)) {
                newFile = file;
                break; // Found a new file, stop searching
            }
        }

        // If a new file was found, add its name to the knownFiles set and return it
        if (newFile != null) {
            knownFiles.add(newFile);
        }

        return newFile;
    }
}