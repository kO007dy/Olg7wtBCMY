// 代码生成时间: 2025-07-31 09:10:09
package com.example.imageresizer;

import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImageResizerService {
    private final int targetWidth;
    private final int targetHeight;

    public ImageResizerService(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    /**
     * Resizes images in a specified directory to the target dimensions.
     *
     * @param sourceDirectoryPath The directory path containing the source images.
     * @return A list of paths to the resized images.
     */
    public List<String> resizeImagesInDirectory(String sourceDirectoryPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(sourceDirectoryPath))) {
            return paths
                .filter(Files::isRegularFile)
                .map(path -> resizeImage(path))
                .filter(imagePath -> imagePath != null)
                .collect(Collectors.toList());
        } catch (IOException e) {
            // Log and handle the exception
            System.err.println("Error walking through the directory: " + e.getMessage());
            return null; // or throw a custom exception if needed
        }
    }

    private String resizeImage(Path imagePath) {
        try {
            BufferedImage originalImage = ImageIO.read(imagePath.toFile());
            // Assume the resizing logic is implemented in a separate method
            BufferedImage resizedImage = resizeImage(originalImage);
            Path resizedImagePath = getResizedImagePath(imagePath);
            ImageIO.write(resizedImage, "jpg", new File(resizedImagePath.toString()));
            return resizedImagePath.toString();
        } catch (IOException e) {
            // Log and handle the exception
            System.err.println("Error resizing image at path: " + imagePath + " - " + e.getMessage());
            return null; // or throw a custom exception if needed
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage) {
        // Implement resizing logic here
        // This is a placeholder, a real implementation would calculate the new dimensions and resize the image
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
        // ... (perform resize operation)
        return resizedImage;
    }

    private Path getResizedImagePath(Path originalImagePath) {
        // Implement logic to determine the path for the resized image
        // This is a placeholder, a real implementation would ensure the path is valid and unique
        return originalImagePath.resolveSibling("resized_" + originalImagePath.getFileName());
    }
}
