// 代码生成时间: 2025-08-23 19:44:28
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

// ApiResponseFormatter 类用于格式化API响应
public class ApiResponseFormatter {

    // 成功响应的格式化
    public static <T> ResponseEntity<Map<String, Object>> successResponse(T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "Request successful");
        response.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 通用错误响应的格式化
    public static ResponseEntity<Map<String, Object>> ErrorResponse(int status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", status);
        response.put("message", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // 错误响应 - 找不到资源
    public static ResponseEntity<Map<String, Object>> notFoundResponse() {
        return ErrorResponse(404, "Resource not found");
    }

    // 错误响应 - 非法参数
    public static ResponseEntity<Map<String, Object>> badRequestResponse(String message) {
        return ErrorResponse(400, message);
    }

    // 错误响应 - 内部服务器错误
    public static ResponseEntity<Map<String, Object>> internalServerErrorResponse(String message) {
        return ErrorResponse(500, message);
    }

    // 测试ApiResponseFormatter类的方法
    public static void main(String[] args) {
        // 测试成功响应
        ResponseEntity<Map<String, Object>> success = successResponse("Test data");
        System.out.println(success.getBody());

        // 测试错误响应
        ResponseEntity<Map<String, Object>> error = notFoundResponse();
        System.out.println(error.getBody());
    }
}
