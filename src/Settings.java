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
        try {
            startUrl = new URL(args[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        maxDepth = Integer.parseInt(args[1]);
        maxPages = Integer.parseInt(args[2]);
        targetDirectory = args[3];
        if(args.length > 4)
            maxThreads = Integer.parseInt(args[4]);
        else
            maxThreads = 5;
    }
}
