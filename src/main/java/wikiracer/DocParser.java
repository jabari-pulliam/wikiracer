package wikiracer;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DocParser {

    private final String CONTENT_ELEMENT_ID = "mw-content-text";
    private static final String BASE_URL = "https://en.wikipedia.org";

    private final OkHttpClient httpClient = new OkHttpClient();


    public Set<String> findLinks(String url) {
        Set<String> links = new HashSet<>();
        Document document;

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = httpClient.newCall(request).execute();

            document = Jsoup.parse(response.body().string());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Element content = document.getElementById(CONTENT_ELEMENT_ID);
        if (content != null) {
            // Find all links with href
            Elements allLinks = document.select("a[href]");
            for (Element e : allLinks) {
                String href = e.attr("href");

                // Only add if it's a link to a wiki page
                if (href.startsWith("/wiki"))
                    links.add(BASE_URL + href);
                else if (href.startsWith(BASE_URL))
                    links.add(href);
            }
        }

        return links;
    }

}
