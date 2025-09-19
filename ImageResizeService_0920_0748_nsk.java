// 代码生成时间: 2025-09-20 07:48:31
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service for resizing images in bulk.
 */
@Service
public class ImageResizeService {

    /**
     * Resizes the images in the specified directory to a new size.
     *
     * @param sourceDirectory The directory containing the source images.
     * @param targetDirectory The directory where the resized images will be saved.
     * @param targetWidth The new width for the images.
     * @param targetHeight The new height for the images.
     * @throws IOException If an I/O error occurs.
     */
    public void resizeImages(String sourceDirectory, String targetDirectory, int targetWidth, int targetHeight) throws IOException {
        // Ensure the target directory exists.
        Path targetDirPath = Paths.get(targetDirectory);
        Files.createDirectories(targetDirPath);

        // List all files in the source directory.
        File sourceDir = new File(sourceDirectory);
        File[] files = sourceDir.listFiles();
        if (files == null) {
            throw new IOException("Source directory is not accessible");
        }

        // Filter for image files and resize each one.
        Arrays.stream(files)
            .filter(file -> isImageFile(file))
            .forEach(file -> resizeImageAndSave(file, targetDirPath, targetWidth, targetHeight));
    }

    /**
     * Checks if the file is an image file.
     *
     * @param file The file to check.
     * @return true if the file is an image, false otherwise.
     */
    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif");
    }

    /**
     * Resizes the image and saves it to the target directory.
     *
     * @param sourceFile The source image file.
     * @param targetDirPath The path to the target directory.
     * @param targetWidth The new width for the image.
     * @param targetHeight The new height for the image.
     * @throws IOException If an I/O error occurs.
     */
    private void resizeImageAndSave(File sourceFile, Path targetDirPath, int targetWidth, int targetHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(sourceFile);
        if (originalImage == null) {
            throw new IOException("Failed to read image: " + sourceFile.getAbsolutePath());
        }

        // Create a new BufferedImage with the target size.
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

        // Draw the original image onto the new BufferedImage, scaling it to fit the new size.
        resizedImage.getGraphics().drawImage(
            originalImage,
            0, 0, targetWidth, targetHeight,
            null
        );

        // Save the resized image.
        String fileName = sourceFile.getName();
        Path targetFilePath = targetDirPath.resolve(fileName);
        ImageIO.write(resizedImage, getExtension(fileName), targetFilePath.toFile());
    }

    /**
     * Extracts the file extension from the file name.
     *
     * @param fileName The file name to extract the extension from.
     * @return The file extension.
     */
    private String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex == -1 ? "jpg" : fileName.substring(dotIndex + 1);
    }
}
