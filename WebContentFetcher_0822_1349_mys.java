// 代码生成时间: 2025-08-22 13:49:58
package com.example.webcontentfetcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class WebContentFetcher {

    /**
     * Fetches the HTML content from the specified URL.
     * 
     * @param url The URL to fetch content from.
     * @return The fetched HTML content as a string.
     * @throws Exception If an error occurs during fetching.
     */
    public String fetchHtmlContent(String url) throws Exception {
        try {
            // Use Jsoup to connect to the URL and fetch the HTML document
            Document document = Jsoup.connect(url).get();
            return document.html();
        } catch (Exception e) {
            // Log and handle the exception
            System.out.println("Error fetching HTML content: " + e.getMessage());
            throw new Exception("Failed to fetch HTML content from URL: " + url, e);
        }
    }

    /**
     * Fetches the text content from the specified URL.
     * 
     * @param url The URL to fetch content from.
     * @return The fetched text content as a string.
     * @throws Exception If an error occurs during fetching.
     */
    public String fetchTextContent(String url) throws Exception {
        try {
            // Use Jsoup to connect to the URL and fetch the text document
            Document document = Jsoup.connect(url).get();
            return document.text();
        } catch (Exception e) {
            // Log and handle the exception
            System.out.println("Error fetching text content: " + e.getMessage());
            throw new Exception("Failed to fetch text content from URL: " + url, e);
        }
    }
}
