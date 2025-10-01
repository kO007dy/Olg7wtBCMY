// 代码生成时间: 2025-10-01 22:10:57
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量文件重命名工具
 * @author Your Name
 */
public class BatchFileRenamer {

    private static final String DIRECTORY_PATH = "path/to/your/directory"; // 需要重命名文件的目录路径
    private static final String NEW_PREFIX = "new_prefix_"; // 新文件名的前缀

    /**
     * 重命名指定目录下的所有文件
     * @param directoryPath 文件目录路径
     * @param newPrefix 新文件名前缀
     */
    public void renameFiles(String directoryPath, String newPrefix) {
        try {
            File directory = new File(directoryPath);
            File[] files = directory.listFiles();
            if (files == null) {
                throw new IllegalArgumentException("The directory path is invalid or there are no files to rename.");
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    String fileName = files[i].getName();
                    String newFileName = newPrefix + (i + 1) + getFileExtension(fileName);
                    File newFile = new File(files[i].getParent(), newFileName);
                    files[i].renameTo(newFile);
                    System.out.println("Renamed ' " + fileName + " ' to ' " + newFileName + " '");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while renaming files: " + e.getMessage());
        }
    }

    /**
     * 获取文件的扩展名
     * @param fileName 原始文件名
     * @return 文件扩展名
     */
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0) {
            return fileName.substring(dotIndex);
        } else {
            return "";
        }
    }

    /**
     * 程序入口点
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        BatchFileRenamer renamer = new BatchFileRenamer();
        renamer.renameFiles(DIRECTORY_PATH, NEW_PREFIX);
    }
}
