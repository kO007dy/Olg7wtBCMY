// 代码生成时间: 2025-09-06 10:43:58
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebPageScraper {

    public static void main(String[] args) {
        SpringApplication.run(WebPageScraper.class, args);
    }

    @Bean
    CommandLineRunner scraper() {
        return args -> {
            try {
                // 抓取网页内容
                String htmlContent = fetchHtml("https://example.com");
                // 解析网页
                Document doc = Jsoup.parse(htmlContent);
                // 提取标题
                String title = doc.title();
                // 提取所有段落
                Elements paragraphs = doc.select("p");
                // 打印结果
                System.out.println("Title: " + title);
                for (Element paragraph : paragraphs) {
                    System.out.println(paragraph.text());
                }
            } catch (Exception e) {
                System.out.println("Error fetching and parsing web page: " + e.getMessage());
            }
        };
    }

    // 抓取网页内容的函数
    private String fetchHtml(String url) throws Exception {
        // 使用Jsoup连接URL并获取HTML
        return Jsoup.connect(url).get();
    }
}
