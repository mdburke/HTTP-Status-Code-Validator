package validator;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger
import org.apache.commons.logging.LogFactory;


public class StatusCodeValidatorSingle {

    private String url; // URL under test

    public StatusCodeValidatorSingle(String url) {
        this.url = url;
    }

    public int checkURLStatusCode() throws IOException {
        /* Remove annoying logs */
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

        WebClient webClient = new WebClient();

        int statusCode;

        try {
            statusCode = webClient.getPage(url).getWebResponse().getStatusCode();
        } catch (FailingHttpStatusCodeException e){
            statusCode = e.getStatusCode();
        }

        webClient.close();

        return statusCode;
    }

}
