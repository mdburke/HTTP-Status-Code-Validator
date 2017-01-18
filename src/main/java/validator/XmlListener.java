package validator;

import java.io.PrintStream;
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

    public XmlListener(PrintStream writer) {
        this.writer = writer;
    }

    @Override
    public void testRunStarted(Description description) {
        printHeader();
    }

    @Override
    public void testRunFinished(Result result) {
        printFooter();
    }

    @Override
    public void testStarted(Description description) {
        getWriter().println("<testcase name=\"" + description.toString() + "\">");
    }

    @Override
    public void testFinished(Description description) {
        getWriter().println("</testcase>");
    }

    @Override
    public void testFailure(Failure failure) {
        getWriter().println("Assertion Failed");
    }

    @Override
    public void testIgnored(Description description) {
        getWriter().println("<skipped />");
    }

    /*
     * Internal methods
     */

    private synchronized PrintStream getWriter() {
        return writer;
    }

    protected void printHeader() {
        getWriter().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        getWriter().println("<testsuites>");
        getWriter().println("<testsuite>");
    }

    protected void printFailure(Failure each, String prefix) {
        getWriter().println(prefix + ") " + each.getTestHeader());
        getWriter().print(each.getTrace());
    }

    protected void printFooter() {
        getWriter().println("</testsuite>");
        getWriter().println("</testsuites>");
    }
}