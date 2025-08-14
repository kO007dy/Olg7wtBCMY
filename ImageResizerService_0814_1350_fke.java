// 代码生成时间: 2025-08-14 13:50:17
// ImageResizerService.java

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageResizerService {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    public List<File> resizeImages(List<MultipartFile> files, int targetWidth, int targetHeight) throws IOException {
        List<File> resizedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                BufferedImage image = ImageIO.read(file.getInputStream());
                if (image == null) {
                    throw new IOException("Unable to read image from file");
                }

                // Resize the image
                BufferedImage resizedImage = resizeImage(image, targetWidth, targetHeight);

                // Create a temporary file
                File resizedFile = File.createTempFile("resized_", ".jpg");
                resizedFile.deleteOnExit(); // Ensure the file is deleted when the JVM exits

                // Write the resized image to the file
                try (OutputStream out = new FileOutputStream(resizedFile)) {
                    ImageIO.write(resizedImage, "jpg", out);
                }

                // Add the resized file to the list
                resizedFiles.add(resizedFile);
            } catch (IOException e) {
                // Handle exception and log
                System.err.println("Error resizing image: " + e.getMessage());
            }
        }

        return resizedFiles;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        // Check if the target dimensions are larger than the original dimensions
        if (targetWidth >= originalImage.getWidth() && targetHeight >= originalImage.getHeight()) {
            return originalImage;
        }

        // Calculate the scaling factor
        double scaleX = (double) targetWidth / originalImage.getWidth();
        double scaleY = (double) targetHeight / originalImage.getHeight();
        double scale = Math.min(scaleX, scaleY);

        // Calculate the new dimensions
        int newWidth = (int) (originalImage.getWidth() * scale);
        int newHeight = (int) (originalImage.getHeight() * scale);

        // Create the resized image
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        // Draw the original image onto the resized image
        resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);

        return resizedImage;
    }
}
