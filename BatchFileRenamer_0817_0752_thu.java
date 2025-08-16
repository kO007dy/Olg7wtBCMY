// 代码生成时间: 2025-08-17 07:52:22
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class to rename multiple files in a directory, based on a pattern.
 * This class provides functionality to batch rename files.
 */
public class BatchFileRenamer {

    private static final String REGEX = "^(.+?)(\d+)(\.\w+)$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * Renames files in the specified directory based on a given pattern.
     * @param directoryPath The path to the directory containing files to rename.
     * @param renamePattern The pattern to match and use for renaming.
     * @throws IOException If an I/O error occurs.
     */
    public void renameFiles(String directoryPath, String renamePattern) throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("The specified path is not a valid directory.");
        }

        File[] files = directory.listFiles();
        if (files == null) {
            throw new IOException("Unable to list files in the directory.");
        }

        int counter = 1;
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                Matcher matcher = PATTERN.matcher(fileName);
                if (matcher.find()) {
                    String baseName = matcher.group(1);
                    String extension = matcher.group(3);
                    String newFileName = String.format(renamePattern, baseName, counter, extension);
                    Path oldPath = file.toPath();
                    Path newPath = Paths.get(directoryPath + File.separator + newFileName);
                    Files.move(oldPath, newPath);
                    counter++;
                }
            }
        }
    }

    /**
     * Main method to test the batch file renamer utility.
     * @param args Command line arguments.
     * @throws IOException If an I/O error occurs during file renaming.
     */
    public static void main(String[] args) throws IOException {
        BatchFileRenamer renamer = new BatchFileRenamer();
        renamer.renameFiles("C:/example/directory", "%s%02d%s");
    }
}
