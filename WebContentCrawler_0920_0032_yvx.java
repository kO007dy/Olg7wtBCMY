// 代码生成时间: 2025-09-20 00:32:59
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class WebContentCrawler {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";

    /**<ol>
     * Fetches the content of a webpage and extracts specific elements.
     * @param urlString The URL of the webpage to crawl.
     * @param query The query to select the elements.
     * @return The extracted content as a string.
     * @throws IOException If an I/O error occurs.
     */
    public String crawlWebContent(String urlString, String query) throws IOException {
        // Connect to the URL and retrieve the document
        Document doc = Jsoup.connect(urlString).userAgent(USER_AGENT).get();

        // Select elements based on the query
        Elements elements = doc.select(query);

        // StringBuilder to accumulate the content
        StringBuilder contentBuilder = new StringBuilder();

        // Iterate over the selected elements and append their text to the contentBuilder
        for (Element element : elements) {
            contentBuilder.append(element.text());
            contentBuilder.append("
"); // Append a newline character
        }

        // Return the accumulated content
        return contentBuilder.toString();
    }
}
