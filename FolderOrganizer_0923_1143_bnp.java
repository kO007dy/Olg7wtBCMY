// 代码生成时间: 2025-09-23 11:43:12
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FolderOrganizer {

    private static final String ORGANIZED_FOLDER_NAME = "organized";

    /**
     * Organize the contents of the given directory into the specified organized folder.
     * 
     * @param directoryPath The path of the directory to be organized.
     * @throws IOException If an I/O error occurs.
     */
    public void organizeDirectory(String directoryPath) throws IOException {
        // Check if the directory exists
        Path directory = Paths.get(directoryPath);
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException("The provided path is not a valid directory: " + directoryPath);
        }

        // Create the organized folder if it doesn't exist
        Path organizedFolder = directory.resolve(ORGANIZED_FOLDER_NAME);
        Files.createDirectories(organizedFolder);

        // Retrieve all files and directories in the given directory
        List<Path> allPaths = Files.walk(directory).sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        // Sort files and directories separately
        List<Path> files = allPaths.stream()
                .filter(Files::isRegularFile)
                .sorted(Comparator.comparing(Path::getFileName))
                .collect(Collectors.toList());

        List<Path> directories = allPaths.stream()
                .filter(Files::isDirectory)
                .sorted(Comparator.comparing(Path::getFileName))
                .collect(Collectors.toList());

        // Move files to the organized folder
        for (Path file : files) {
            Path targetFile = organizedFolder.resolve(file.getFileName());
            Files.move(file, targetFile);
        }

        // Move directories to the organized folder
        for (Path directoryPath1 : directories) {
            if (!directoryPath1.equals(organizedFolder)) { // Avoid moving the organized folder itself
                Path targetDirectory = organizedFolder.resolve(directoryPath1.getFileName());
                Files.move(directoryPath1, targetDirectory);
            }
        }
    }

    public static void main(String[] args) {
        FolderOrganizer organizer = new FolderOrganizer();
        try {
            organizer.organizeDirectory("./path/to/directory"); // Replace with the actual directory path
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
