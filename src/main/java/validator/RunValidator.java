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
        System.out.println(result);
    }
}

