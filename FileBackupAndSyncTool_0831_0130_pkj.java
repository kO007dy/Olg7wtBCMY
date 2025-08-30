// 代码生成时间: 2025-08-31 01:30:58
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileBackupAndSyncTool {

    private final ResourceLoader resourceLoader;

    public FileBackupAndSyncTool(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Backup a file to a specified backup directory.
     * 
     * @param sourceFilePath The path of the file to be backed up.
     * @param backupDirPath  The path of the backup directory.
     * @return The path of the backed-up file.
     * @throws IOException If an I/O error occurs.
     */
    public Path backupFile(String sourceFilePath, String backupDirPath) throws IOException {
        Path sourcePath = Paths.get(sourceFilePath);
        Path backupDir = Paths.get(backupDirPath);
        Path backupPath = backupDir.resolve(sourcePath.getFileName());

        if (!Files.exists(backupDir)) {
            Files.createDirectories(backupDir);
        }

        Files.copy(sourcePath, backupPath, StandardCopyOption.REPLACE_EXISTING);
        return backupPath;
    }

    /**
     * Synchronize a directory to a specified target directory.
     * 
     * @param sourceDirPath  The path of the source directory.
     * @param targetDirPath  The path of the target directory.
     * @throws IOException If an I/O error occurs.
     */
    public void syncDirectory(String sourceDirPath, String targetDirPath) throws IOException {
        Path sourceDir = Paths.get(sourceDirPath);
        Path targetDir = Paths.get(targetDirPath);

        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }

        Files.walk(sourceDir).forEach(sourcePath -> {
            try {
                Path targetPath = targetDir.resolve(sourceDir.relativize(sourcePath));
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Error syncing file: " + sourcePath, e);
            }
        });
    }

    // Additional methods can be added here for more advanced features or error handling.

}
