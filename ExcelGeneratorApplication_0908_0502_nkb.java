// 代码生成时间: 2025-09-08 05:02:17
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@SpringBootApplication
@RestController
public class ExcelGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelGeneratorApplication.class, args);
    }

    @GetMapping("/generate-excel")
    public void generateExcel(HttpServletResponse response) {
        try {
            // Set the header to download
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("example.xlsx", "UTF-8") + "");

            // Create a workbook and a sheet
            Workbook workbook = new XSSFWorkbook();
            // You can populate the sheet with data here
            // For demonstration, a simple header is added
            workbook.createSheet("Example Sheet");

            // Write the workbook to the response output stream
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            // Handle the exception, for example, log it or return an error response
            e.printStackTrace();
        }
    }
}