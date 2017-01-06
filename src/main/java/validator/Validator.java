package validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@RunWith(Parameterized.class)
public class Validator {

    private String urlUnderTest;
    /* Show actual test logs */
    private static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(StatusCodeValidatorSingle.class);

    public Validator(String urlUnderTest) {
        this.urlUnderTest = urlUnderTest;
    }

    @Parameterized.Parameters()
    public static Iterable<Object[]> generateParameters() {
        List<Object[]> result = new ArrayList<>();
        UrlList urlList = new UrlList();
        for (String url : urlList.getUrlList()) {
            result.add(new Object[] { url });
        }

        return result;
    }

    @Rule public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            logger.info("Run Test {}...", method.getName());
        }
        public void succeeded(FrameworkMethod method) {
            logger.info("Test {} succeeded.", method.getName());
        }
        public void failed(Throwable e, FrameworkMethod method) {
            logger.error("Test {} failed with {}.", method.getName(), e);
        }
    };

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
