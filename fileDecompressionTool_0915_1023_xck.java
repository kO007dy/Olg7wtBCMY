// 代码生成时间: 2025-09-15 10:23:36
package com.example.filedecompressiontool;

import org.springframework.stereotype.Component;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class FileDecompressionTool {

    /**
     * Decompresses a zip file to a specified directory.
     *
     * @param zipFilePath The path of the zip file to decompress.
     * @param destDirectoryPath The path of the destination directory to extract files to.
     * @return A boolean indicating the success of the decompression operation.
     */
    public boolean decompressZipFile(String zipFilePath, String destDirectoryPath) {
        try {
            File destDirectory = new File(destDirectoryPath);
            if (!destDirectory.exists()) {
                destDirectory.mkdirs();
            }

            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipIn.getNextEntry();
            // Loop through the entry in the zip file
            while (entry != null) {
                String filePath = destDirectoryPath + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // If the entry is a file, extract it
                    extractFile(zipIn, filePath);
                } else {
                    // If the entry is a directory, make the directory
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Extracts a file from the zip input stream.
     *
     * @param zipIn The zip input stream to read from.
     * @param filePath The path to write the file to.
     * @throws IOException If an I/O error occurs.
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(zipIn);
        OutputStream output = new FileOutputStream(filePath);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = bis.read(bytes)) >= 0) {
            output.write(bytes, 0, length);
        }
        output.close();
        bis.close();
    }
}
