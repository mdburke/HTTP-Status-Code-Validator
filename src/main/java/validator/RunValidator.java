package validator;

public class RunValidator {
    public static void main (String[] args) {
        String testDataPath;

        if (args.length == 1) {
            testDataPath = args[0];
        } else {
            System.out.println("Please enter an argument (only one) for the test path data.");
            System.exit(0);
        }


    }
}

