import java.io.File;

/**
 * Created by den on 29.10.14.
 */
public class Main {
    public static void main(String[] args) {
        Settings.initialize(args);
        UrlsQueue queue = new UrlsQueue();
        queue.add(Settings.startUrl, 0);
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < Settings.maxDepth; i++)
        {
            queue.rake(i);
        }
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime);
    }
}
