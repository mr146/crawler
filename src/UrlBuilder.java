import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by den on 30.10.14.
 */
public class UrlBuilder {
    public static URL buildUrl(URL baseUrl, String ref) {
        if(isBad(ref))
            return null;
        try {
            return new URL(baseUrl, ref);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private static String normalize(String initial)
    {
        URL url;
        try {
            url = new URL(initial);
        } catch (MalformedURLException e) {
            return null;
        }
        try {
            return url.toURI().toString();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static boolean isBad(String ref)
    {
        return (!ref.startsWith("http") && ref.contains(":")) || ref.endsWith(".css") || ref.endsWith(".ico");
    }
}
