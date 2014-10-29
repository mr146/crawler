import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

/**
 * Created by den on 29.10.14.
 */
public class PageLoader implements Runnable {
    private UrlsQueue queue;
    private URL url;
    private int currentDepth;

    public PageLoader(UrlsQueue queue, URL url, int currentDepth) {

        this.queue = queue;
        this.url = url;
        this.currentDepth = currentDepth;
    }

    public void run() {
        Document document = null;
        try {
            document = Jsoup.parse(url, 10000);
        } catch (IOException e) {
            System.err.println(String.format("Failed to load page with url %s", url));
            return;
        }
        Elements href = document.getElementsByAttribute("href");
        FileOutputStream os = null;
        try {
            String filename = Settings.targetDirectory + UUID.randomUUID().toString() + ".html";
            String documentValue = document.toString();
            File file = new File(filename);
            file.createNewFile();
            os = new FileOutputStream(file);
            os.write(documentValue.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Element element : href) {
            String ref = element.attr("href");
            URL newUrl = UrlBuilder.buildUrl(url, ref);
            queue.add(newUrl, currentDepth + 1);
        }
    }
}
