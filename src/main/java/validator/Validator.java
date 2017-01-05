package validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@RunWith(Parameterized.class)
public class Validator {

    private String urlUnderTest;

    public Validator(String urlUnderTest) {
        this.urlUnderTest = urlUnderTest;
    }

    @Parameterized.Parameters()
    public static Iterable<Object[]> generateParameters() {
        List<Object[]> result = new ArrayList<>();
        UrlList urlList = new UrlList("TestUrls.csv");
        for (String url : urlList.getUrlList()) {
            result.add(new Object[] { url });
        }

        return result;
    }

    @Test
    public void testUrl() {
        StatusCodeValidatorSingle validator = new StatusCodeValidatorSingle(urlUnderTest);

        try {
            Assert.assertEquals(200, validator.checkURLStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
