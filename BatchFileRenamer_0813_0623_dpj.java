// 代码生成时间: 2025-08-13 06:23:42
// BatchFileRenamer.java

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;

/**
 * 批量文件重命名工具类，用于重命名指定目录下的所有文件。
 *
 * @author YourName
 * @version 1.0
 */
public class BatchFileRenamer {

    private String directoryPath; // 目录路径
    private String fileSuffix;   // 文件后缀
    private String newPrefix;    // 新的文件名前缀
    private String newSuffix;    // 新的文件后缀

    /**
     * 构造方法，初始化目录路径、文件后缀、新文件名前缀和后缀。
     *
     * @param directoryPath 目录路径
     * @param fileSuffix 文件后缀
     * @param newPrefix 新的文件名前缀
     * @param newSuffix 新的文件后缀
     */
    public BatchFileRenamer(String directoryPath, String fileSuffix, String newPrefix, String newSuffix) {
        this.directoryPath = directoryPath;
        this.fileSuffix = fileSuffix;
        this.newPrefix = newPrefix;
        this.newSuffix = newSuffix;
    }

    /**
     * 重命名文件的方法。
     *
     * @return 重命名成功的文件数量
     */
    public int renameFiles() {
        int renamedCount = 0;
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("The specified directory does not exist or is not a directory.");
            return renamedCount;
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(fileSuffix));
        if (files == null) {
            System.err.println("Error listing files in the directory.");
            return renamedCount;
        }

        for (File file : files) {
            try {
                String fileNameWithoutExtension = StringUtils.stripFilenameExtension(file.getName());
                String newFileName = newPrefix + fileNameWithoutExtension + newSuffix;
                Path oldPath = file.toPath();
                Path newPath = oldPath.resolveSibling(newFileName);
                Files.move(oldPath, newPath);
                renamedCount++;
                System.out.println("Renamed: " + file.getName() + " to " + newFileName);
            } catch (Exception e) {
                System.err.println("Error renaming file: " + file.getName() + ". Error: " + e.getMessage());
            }
        }

        return renamedCount;
    }

    /**
     * Main method to run the batch file renamer.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println("Usage: BatchFileRenamer <directoryPath> <fileSuffix> <newPrefix> <newSuffix>");
            return;
        }

        String directoryPath = args[0];
        String fileSuffix = args[1];
        String newPrefix = args[2];
        String newSuffix = args[3];

        BatchFileRenamer renamer = new BatchFileRenamer(directoryPath, fileSuffix, newPrefix, newSuffix);
        int renamedCount = renamer.renameFiles();
        System.out.println("Total files renamed: " + renamedCount);
    }
}
