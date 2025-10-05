// 代码生成时间: 2025-10-05 17:39:52
package com.example.imagefilter;

import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageFilterEngine {

    private final String[] supportedFormats = new String[] {"jpg", "jpeg", "png", "gif"};

    public BufferedImage applyFilter(BufferedImage image, String filter) throws IOException {
        // Check if the image is not null
        if (image == null) {
            throw new IllegalArgumentException("The image cannot be null.");
        }

        // Check if filter name is valid
        if (filter == null || !filter.matches("^(BW|Grayscale)$")) {
            throw new IllegalArgumentException("Invalid filter name. Only 'BW' and 'Grayscale' are supported.");
        }

        // Apply the filter to the image
        switch (filter) {
            case "BW":
                return applyBlackAndWhiteFilter(image);
            case "Grayscale":
                return applyGrayscaleFilter(image);
            default:
                throw new IllegalArgumentException("Unsupported filter: " + filter);
        }
    }

    // Apply a black and white filter to the image
    private BufferedImage applyBlackAndWhiteFilter(BufferedImage image) {
        // Convert the image to grayscale and then to black and white
        BufferedImage grayscaleImage = applyGrayscaleFilter(image);
        for (int y = 0; y < grayscaleImage.getHeight(); y++) {
            for (int x = 0; x < grayscaleImage.getWidth(); x++) {
                int pixel = grayscaleImage.getRGB(x, y);
                int newPixel = (pixel & 0xFF00FF) | ((pixel & 0x00FF00) >> 1) | ((pixel & 0x0000FF) >> 2);
                grayscaleImage.setRGB(x, y, newPixel);
            }
        }
        return grayscaleImage;
    }

    // Apply a grayscale filter to the image
    private BufferedImage applyGrayscaleFilter(BufferedImage image) {
        BufferedImage grayscaleImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int r = (pixel >> 16) & 0xFF;
                int g = (pixel >> 8) & 0xFF;
                int b = pixel & 0xFF;
                int gray = (int) (r * 0.3 + g * 0.59 + b * 0.11);
                grayscaleImage.setRGB(x, y, (gray << 16) + (gray << 8) + gray);
            }
        }
        return grayscaleImage;
    }

    // Load an image from a file to a BufferedImage object
    public BufferedImage loadImageFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        String extension = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();

        // Check if the file is supported
        if (!isSupportedFormat(extension)) {
            throw new IOException("Unsupported image format.");
        }

        // Load the image from the file
        return ImageIO.read(file);
    }

    // Check if the image format is supported
    private boolean isSupportedFormat(String extension) {
        for (String format : supportedFormats) {
            if (format.equals(extension)) {
                return true;
            }
        }
        return false;
    }
}
