package validator;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import java.io.IOException;


public class StatusCodeValidatorSingle {

    private String url; // URL under test

    public StatusCodeValidatorSingle(String url) {
        this.url = url;
    }

    public int checkURLStatusCode() throws IOException {
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
