package validator;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * This listener runs with the tests and creates the necessary xml data for consumption by Junit.
 */
public class XmlListener extends RunListener {

    private final PrintStream writer;
    private double time;
    private int numTests;
    private int numErrors;
    private int numSkipped;
    private int numFailures;
    private ArrayList<String> body = new ArrayList<>();
    private String currentTestCase;

    public XmlListener(PrintStream writer) {
        this.writer = writer;
    }

    @Override
    public void testRunStarted(Description description) {
    }

    @Override
    public void testRunFinished(Result result) {
        printHeader(result);
        printBody();
        printFooter();
    }

    @Override
    public void testStarted(Description description) {
        numTests += 1;
        currentTestCase = "<testcase name=\"" + description.toString() + "\"" +
                          " classname=\"" + description.getClassName() + "\"" +
                          ">";
    }

    @Override
    public void testFinished(Description description) {
        currentTestCase += "</testcase>";
        body.add(currentTestCase);
        currentTestCase = "";
    }

    @Override
    public void testFailure(Failure failure) {
        numFailures += 1;
        currentTestCase += "<failure message=\"Assertion failed\"></failure>";
    }

    @Override
    public void testIgnored(Description description) {
        numSkipped += 1;
        currentTestCase += "</skipped>";
    }

    /*
     * Internal methods
     */

    private synchronized PrintStream getWriter() {
        return writer;
    }

    protected void printBody() {
        for (String line : body) {
            getWriter().println(line);
        }
    }

    protected void printHeader(Result result) {
        getWriter().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        getWriter().println("<testsuite name=\"validator.TestRunValidator\"" +
                " time=\"" + result.getRunTime() + "\"" +
                " tests=\"" + result.getRunCount() + "\"" +
                " skipped=\"" + result.getIgnoreCount() + "\"" +
                " failures=\"" + result.getFailureCount() + "\"" +
                ">");
    }

    protected void printFailure(Failure each, String prefix) {
        getWriter().println(prefix + ") " + each.getTestHeader());
        getWriter().print(each.getTrace());
    }

    protected void printFooter() {
        getWriter().println("</testsuite>");
    }
}