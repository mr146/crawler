import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by den on 30.10.14.
 */
public class Settings {
    public static int maxDepth;
    public static int maxThreads;
    public static int maxPages;
    public static URL startUrl;
    public static String targetDirectory;

    public static void initialize(String[] args) {
        maxDepth = 3;
        maxThreads = 1;
        maxPages = 500;
        targetDirectory = "/Users/den/Desktop/tmp/";
        try {
            startUrl = new URL("http://acm.timus.ru");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
