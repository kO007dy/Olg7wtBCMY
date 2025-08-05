// 代码生成时间: 2025-08-06 07:42:07
// UserPermissionManagement.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

// 启动类
@SpringBootApplication
@EnableDiscoveryClient
public class UserPermissionManagement {
    
    public static void main(String[] args) {
        SpringApplication.run(UserPermissionManagement.class, args);
    }
}

// 控制器类
@RestController
public class PermissionController {

    private static final Map<String, String> userPermissions = new HashMap<>();

    static {
        // 初始化用户权限数据
        userPermissions.put("user1", "ROLE_USER");
        userPermissions.put("admin", "ROLE_ADMIN");
    }

    // 获取用户权限
    @GetMapping("/permissions/{username}")
    public String getUserPermission(@PathVariable String username) {
        try {
            if (userPermissions.containsKey(username)) {
                return userPermissions.get(username);
            } else {
                throw new Exception("User not found");
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
