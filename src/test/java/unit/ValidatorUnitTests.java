package unit;

import org.junit.Assert;
import org.junit.Test;
import validator.StatusCodeValidatorSingle;
import validator.UrlList;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ValidatorUnitTests {
    @Test
    public void test200StatusCode() {
        StatusCodeValidatorSingle validator = new StatusCodeValidatorSingle("http://www.pixotech.com");

        try {
            Assert.assertEquals(200, validator.checkURLStatusCode());
        } catch (IOException e) {
            System.out.println("IOException thrown in validator.checkURLStatusCode()");
            e.printStackTrace();
        }
    }

    @Test
    public void test404StatusCode() {
        StatusCodeValidatorSingle validator = new StatusCodeValidatorSingle("http://www.pixotech.com/gobble");

        try {
            Assert.assertEquals(404, validator.checkURLStatusCode());
        } catch (IOException e) {
            System.out.println("IOException thrown in validator.checkURLStatusCode()");
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateList() {
        UrlList urlList = new UrlList("TestUrls.csv");
        List<String> correctList = new ArrayList<>();
        String baseString = "http://ahs.uic.i.pixotech.com";

        correctList.add(baseString + "/news");
        correctList.add(baseString + "/events");
        correctList.add(baseString + "/physical-therapy");
        correctList.add(baseString + "/physical-therapy/events");

        Assert.assertArrayEquals(correctList.toArray(), urlList.getUrlList().toArray());
        System.out.println(urlList.getUrlList());
    }
}
