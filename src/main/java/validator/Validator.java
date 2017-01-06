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

/**
 * Class that runs all of the iterations. This class is using junit @RunWith(Paramterized) to parameterize the tests
 * at runtime. This way we can use a csv of urls from any place passed in as a command line argument and the tests will
 * be created at runtime.
 */
@RunWith(Parameterized.class)
public class Validator {

    /* This will be a different url for each iteration */
    private String urlUnderTest;

    /* Create logger */
    private static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(StatusCodeValidatorSingle.class);

    /* Constructor that takes a single String parameter for the url */
    public Validator(String urlUnderTest) {
        this.urlUnderTest = urlUnderTest;
    }

    /*
    *  This does the magic parameterization. To customize with other parameters, add items to the object in the
    *  result.add line.
    *  Courtesy of http://meri-stuff.blogspot.com/2014/08/junit-dynamic-tests-generation.html
    */
    @Parameterized.Parameters()
    public static Iterable<Object[]> generateParameters() {
        List<Object[]> result = new ArrayList<>();
        UrlList urlList = new UrlList();
        for (String url : urlList.getUrlList()) {
            result.add(new Object[] { url });
        }

        return result;
    }

    /* Logging rules */
    @Rule public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            logger.info("Starting Test {}...", urlUnderTest);
        }
        public void succeeded(FrameworkMethod method) {
            logger.info("Test {} succeeded.", urlUnderTest);
        }
        public void failed(Throwable e, FrameworkMethod method) {
            logger.error("Test {} failed with {}.", urlUnderTest, e);
        }
    };

    /*
     * The actual test which will become parameterized and run on each url. Simply asserts against a 200 status code.
     * Also passes in the urlUnderTest so the output will show this if there is a failure.
    */
    @Test
    public void testUrl() {
        StatusCodeValidatorSingle validator = new StatusCodeValidatorSingle(urlUnderTest);

        try {
            Assert.assertEquals(urlUnderTest,200, validator.checkURLStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
