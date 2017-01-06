package validator;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.LogFactory;

/* Get a status code for a single url */
public class StatusCodeValidatorSingle {

    private String url; // URL under test

    public StatusCodeValidatorSingle(String url) {
        this.url = url;
    }

    public int checkURLStatusCode() throws IOException {
        WebClient webClient = new WebClient();
        int statusCode;

        /* Remove annoying logs about html and css */
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

        /*
         * Try to get the status code. Need to catch FailingHttpStatusCodeException so the program doesn't exit and
         * so that I can grab that failed status code.
         */
        try {
            statusCode = webClient.getPage(url).getWebResponse().getStatusCode();
        } catch (FailingHttpStatusCodeException e){
            statusCode = e.getStatusCode();
        } finally {
            webClient.close();
        }

        return statusCode;
    }

}
