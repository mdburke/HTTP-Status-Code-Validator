package validator;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class TestRunValidator {
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
    public void testNegativeDeletePreviousLogFile() {
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

    @Test
    public void testParseCommandLineOneArg() {

        RunValidator.parseCommandLine(new String[] {"testDataPath.csv"});

        Assert.assertEquals(RunValidator.getTestDataPath(), "testDataPath.csv");
        Assert.assertEquals(RunValidator.getLogPath(), "logs.xml");
    }

    @Test
    public void testNegativeParseCommandLineOneArg() {
        RunValidator.parseCommandLine(new String[] {"NegativeTestDataPath.csv"});

        Assert.assertFalse("testDataPath.csv".equals(RunValidator.getTestDataPath()));
        Assert.assertFalse("fakePath.xml".equals(RunValidator.getLogPath()));
    }

    @Test
    public void testParseCommandLineTwoArgs() {
        RunValidator.parseCommandLine(new String[] {"testDataPath.csv", "logPath.xml"});

        Assert.assertEquals(RunValidator.getTestDataPath(), "testDataPath.csv");
        Assert.assertEquals(RunValidator.getLogPath(), "logPath.xml");
    }

    @Test
    public void testNegativeParseCommandLineTwoArgs() {
        RunValidator.parseCommandLine(new String[] {"NegativeTestDataPath.csv", "fakePath.xml"});

        Assert.assertFalse("testDataPath.csv".equals(RunValidator.getTestDataPath()));
        Assert.assertFalse("logPath.xml".equals(RunValidator.getLogPath()));
    }

    @Test
    public void testCreateFile() {
        RunValidator.createFile("testCreateFile.xml");

        Assert.assertTrue(Files.exists(Paths.get("testCreateFile.xml")));

        try {
            Files.deleteIfExists(Paths.get("testCreateFile.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNegativeCreateFile() {
        RunValidator.createFile("testCreateFile.xml");

        Assert.assertFalse(Files.exists(Paths.get("testCreateFfile.xml")));

        try {
            Files.deleteIfExists(Paths.get("testCreateFile.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
