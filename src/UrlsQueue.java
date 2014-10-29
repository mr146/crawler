import java.net.URL;
import java.util.concurrent.*;

/**
 * Created by den on 30.10.14.
 */
public class UrlsQueue {
    private ConcurrentHashMap<URL, Object> urls;
    private Object lockObject;
    private int counter = 0;
    private ArrayBlockingQueue<URL>[] innerQueue;

    public UrlsQueue() {
        lockObject = new Object();
        urls = new ConcurrentHashMap<URL, Object>();
        innerQueue = new ArrayBlockingQueue[Settings.maxDepth];
        for (int i = 0; i < Settings.maxDepth; i++)
            innerQueue[i] = new ArrayBlockingQueue<URL>(Settings.maxPages);
    }

    public void add(URL url, int depth) {
        if (url == null)
            return;
        if (depth == Settings.maxDepth)
            return;
        Object putResult = urls.putIfAbsent(url, new Object());
        if (putResult != null)
            return;
        boolean good;
        synchronized (lockObject) {
            good = counter < Settings.maxPages;
            if (good)
                counter++;
        }
        if (good)
            innerQueue[depth].add(url);
    }

    public void rake(int level) {
        ExecutorService executorService = Executors.newFixedThreadPool(Settings.maxThreads);
        for(URL url : innerQueue[level]) {
            executorService.execute(new PageLoader(this, url, level));
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
