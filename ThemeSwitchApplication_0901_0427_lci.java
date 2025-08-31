// 代码生成时间: 2025-09-01 04:27:12
package com.example.themeswitch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class ThemeSwitchApplication {

    private Map<String, String> themes = new HashMap<>();

    public ThemeSwitchApplication() {
        // Initialize themes with key-value pairs. Key is the theme name, value is the CSS file path.
        themes.put("Light", "/css/light.css");
        themes.put("Dark", "/css/dark.css");
    }

    @GetMapping("/switch-theme")
    public String switchTheme(HttpServletRequest request) {
        String currentTheme = request.getParameter("theme");
        try {
            if (themes.containsKey(currentTheme)) {
                // Set the theme in the session for persistence across requests.
                request.getSession().setAttribute("theme", currentTheme);
                return "Theme switched to: " + currentTheme;
            } else {
                return "Error: Theme not found.";
            }
        } catch (Exception e) {
            // Log the exception and return a generic error message.
            // In production, consider using a logging framework like SLF4J with Logback.
            return "Error: Unable to switch theme.";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ThemeSwitchApplication.class, args);
    }
}
