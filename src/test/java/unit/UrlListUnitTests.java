package unit;

import org.junit.Assert;
import org.junit.Test;
import validator.UrlList;

import java.util.ArrayList;
import java.util.List;

public class UrlListUnitTests {
    @Test
    public void testCreateList() {
        UrlList urlList = new UrlList("UnitTestUrls.csv");
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
