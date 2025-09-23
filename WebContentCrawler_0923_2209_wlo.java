// 代码生成时间: 2025-09-23 22:09:39
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class WebContentCrawler {

    public static void main(String[] args) {
        SpringApplication.run(WebContentCrawler.class, args);
    }

    // Bean for WebClient to perform HTTP requests
    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    // ApplicationRunner to run the crawler when the application starts
    @Bean
    public ApplicationRunner applicationRunner(WebClient webClient) {
        return args -> {
            // URL to crawl
            String url = "https://example.com";
            try {
                // Perform a GET request to the URL
                Mono<String> webContentMono = webClient.get()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(String.class);

                // Block and wait for the result (not recommended in production due to blocking I/O)
                String webContent = webContentMono.block();

                // Process the web content here
                System.out.println("Web content: 
" + webContent);
            } catch (Exception e) {
                // Error handling
                System.err.println("Error fetching web content: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
