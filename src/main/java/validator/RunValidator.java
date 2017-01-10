package validator;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.Result;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/* Main method that coordinates all tests */
public class RunValidator {

    /* Needed to be static so UrlList.createList() could access it without an instance. */
    static String testDataPath;
    static String logPath;

    public static void main (String[] args) {

        /* Parse command line */
        if (args.length == 1) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                System.out.println("Please enter the path for the test data and optionally the path where the tests" +
                        "should output the logs.");
                System.exit(0);
            } else {
                testDataPath = args[0];
                logPath = "logs.xml";
            }
        } else if (args.length == 2) {
            testDataPath = args[0];
            logPath = args[1];
        } else {
            System.out.println("Invalid number of arguments. 1 or 2 only.");
            System.exit(0);
        }

        /* Create log file */
        PrintStream writer;
        try {
           writer = new PrintStream(new FileOutputStream(logPath, false));
        } catch (IOException e) {
            e.printStackTrace();
            writer = null;
        }

        /* Run tests */
        JUnitCore core = new JUnitCore();
        core.addListener(new XmlListener(writer));
        Result result = core.run(Validator.class);
        /*
         *   Avoiding synchronization but still need some time for last test to finish.
         *   Was seeing the final print before the last logger 1 out of 3 or 4 times.
        */
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* Print results */
        System.out.println("Tests finished.");
        System.out.println("Failure Count: " + result.getFailureCount());
        if (result.getFailureCount() > 0) {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.getMessage());
            }
        }

        try {
            writer.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

