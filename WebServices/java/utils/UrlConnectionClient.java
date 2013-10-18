import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.RuntimeException;

/**
 * Java 6 version
 */
public class UrlConnectionClient {
  public static void main(String[] args) {
    final boolean showInfo = false;

    // usage
    if(args.length < 1) {
      System.err.println("Usage: UrlConnectionClient <url>");
      return;
    }
    try {
      // Parse the URL
      URL url = new URL(args[0].trim());
      
      // Connect
      URLConnection conn = url.openConnection();

      // Read and print the response
      if(showInfo) System.out.println("RESPONSE:\n");
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String next_record = null;
      while((next_record = reader.readLine()) != null) {
        System.out.println(next_record);
      }

      // close
      reader.close();
    }
    catch(MalformedURLException e) {
      throw new RuntimeException("Please try again. Bad URL.\n" + e);
    }
    catch(UnknownHostException e) {
      throw new RuntimeException("Please try again. Unknown host.\n" + e);
    }
    catch(IOException e) {
      throw new RuntimeException("Please try again. Something's wrong.\n" + e);
    }
  }
}

