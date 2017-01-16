package validator;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class RunValidatorTests {
    @Test
    public void testDeletePreviousLogFile() {
        /* Create test file */
        Path path = Paths.get("testFile.txt");

        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Test method */
        RunValidator.deletePreviousLogFile(path.toString());

        Assert.assertFalse(Files.exists(path));

        /* teardown */
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFailDeletePreviousLogFile() {
        /* Create test file */
        Path path = Paths.get("testFile.txt");

        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Test method */
        RunValidator.deletePreviousLogFile(path.toString() + "badPath");

        Assert.assertTrue(Files.exists(path));

        /* teardown */
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
