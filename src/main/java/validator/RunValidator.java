package validator;

import org.junit.internal.JUnitSystem;
import org.junit.internal.XmlListener;
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

    public static void main (String[] args) {

        /* Parse command line */
        if (args.length == 1) {
            testDataPath = args[0];
        } else {
            System.out.println("Please enter an argument (only one) for the test path data.");
            System.exit(0);
        }

        /* Create log file */
        PrintStream writer;
        try {
           writer = new PrintStream(new FileOutputStream("logs.xml", true));
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

