package unit;

import org.junit.Assert;
import org.junit.Test;
import validator.StatusCodeValidatorSingle;

import java.io.IOException;


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
}
