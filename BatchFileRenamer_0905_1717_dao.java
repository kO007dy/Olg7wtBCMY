// 代码生成时间: 2025-09-05 17:17:16
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
# 改进用户体验
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// BatchFileRenamer is a tool for batch renaming files in a directory.
public class BatchFileRenamer {

    // Method to rename files in the given directory
    public void renameFiles(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a valid directory.");
        }

        // List all files in the directory
        File[] files = directory.listFiles();
        if (files == null) {
# NOTE: 重要实现细节
            throw new IOException("Unable to list files in the directory.");
        }

        // Rename files in a sequence
        for (int i = 0; i < files.length; i++) {
# 优化算法效率
            if (files[i].isFile()) {
                try {
                    String newName = "file_" + i + files[i].getName().substring(files[i].getName().lastIndexOf('.'));
                    Path sourcePath = Paths.get(files[i].getAbsolutePath());
                    Path targetPath = Paths.get(directoryPath + "/" + newName);

                    // Rename the file
                    Files.move(sourcePath, targetPath);

                    // Log the renamed file
                    System.out.println("Renamed file from " + files[i].getName() + " to " + newName);
                } catch (IOException e) {
                    // Handle the exception for each failed file rename
                    System.err.println("Error renaming file: " + files[i].getName() + ". Error: " + e.getMessage());
                }
            }
        }
    }
# TODO: 优化性能

    // Main method to run the batch renaming tool
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: BatchFileRenamer <directory-path>");
            return;
        }
# 改进用户体验

        BatchFileRenamer renamer = new BatchFileRenamer();
        try {
            renamer.renameFiles(args[0]);
# NOTE: 重要实现细节
        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
# FIXME: 处理边界情况
}
# 优化算法效率
