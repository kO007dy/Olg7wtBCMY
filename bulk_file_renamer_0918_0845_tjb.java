// 代码生成时间: 2025-09-18 08:45:01
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BulkFileRenamer {
    
    private static final String DIRECTORY_PATH = "/path/to/directory"; // Specify the directory path
    private static final String PATTERN = ".*"; // Pattern to match all files
    private static final String NEW_PREFIX = "new_"; // New prefix for files
    private static final int START_INDEX = 1; // Starting index for new file names
    private static final String EXTENSION = ".ext"; // File extension
    
    /**
     * Main method to initiate the bulk file renaming process
     */
    public static void main(String[] args) {
        try {
            List<File> filesToRename = listFilesMatchingPattern(DIRECTORY_PATH, PATTERN);
            renameFiles(filesToRename);
        } catch (Exception e) {
            System.err.println("Error occurred during file renaming: " + e.getMessage());
        }
    }
    
    /**
     * Lists all files in the given directory that match the specified pattern
     *
     * @param directoryPath the path to the directory
     * @param pattern the pattern to match files
     * @return a list of files matching the pattern
     * @throws Exception if an error occurs while listing files
     */
    private static List<File> listFilesMatchingPattern(String directoryPath, String pattern) throws Exception {
        Path path = Paths.get(directoryPath);
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("The path is not a directory: " + directoryPath);
        }
        
        return Files.walk(path)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().matches(pattern))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
    
    /**
     * Renames the provided list of files with a new naming scheme
     *
     * @param files the list of files to rename
     * @throws Exception if an error occurs during renaming
     */
    private static void renameFiles(List<File> files) throws Exception {
        int index = START_INDEX;
        for (File file : files) {
            String fileName = file.getName();
            String newFileName = NEW_PREFIX + index + EXTENSION;
            File newFile = new File(file.getParent(), newFileName);
            if (file.renameTo(newFile)) {
                System.out.println("Renamed file: " + fileName + " to " + newFileName);
                index++;
            } else {
                throw new Exception("Failed to rename file: " + fileName);
            }
        }
    }
}