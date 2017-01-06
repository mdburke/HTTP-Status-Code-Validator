package validator;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.Result;

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

        /* Run tests */
        Result result = JUnitCore.runClasses(Validator.class);

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
    }
}

