package validator;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class RunValidator {
    static String testDataPath;

    public static void main (String[] args) {

        if (args.length == 1) {
            testDataPath = args[0];
        } else {
            System.out.println("Please enter an argument (only one) for the test path data.");
            System.exit(0);
        }

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

        System.out.println("Tests finished.");
        System.out.println("Failure Count: " + result.getFailureCount());
        if (result.getFailureCount() > 0) {
            System.out.println(result.getFailures());
        }
    }
}

