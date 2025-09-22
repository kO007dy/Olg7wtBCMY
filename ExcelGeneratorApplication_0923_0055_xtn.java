// 代码生成时间: 2025-09-23 00:55:32
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class ExcelGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelGeneratorApplication.class, args);
    }

    @GetMapping("/generateExcel")
    public void generateExcel(HttpServletResponse response) {
        try {
            // Set the content type to Excel file
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=example.xlsx");

            // Create a new Excel workbook
            Workbook workbook = new XSSFWorkbook();
            
            // Create a new sheet
            Sheet sheet = workbook.createSheet("Example Sheet");

            // Create a row and cells
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Hello");

            // Write data to the Excel file
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);

            // Close the workbook and outputStream
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}