package org.junit.internal;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class XmlListener extends RunListener {

    private final PrintStream writer;

    public XmlListener(JUnitSystem system) {
        this(system.out());
    }

    public XmlListener(PrintStream writer) {
        this.writer = writer;
    }

    @Override
    public void testRunFinished(Result result) {
        printHeader(result);
        printFailures(result);
        printFooter(result);
    }

    @Override
    public void testStarted(Description description) {
    }

    @Override
    public void testFailure(Failure failure) {
        writer.append('E');
    }

    @Override
    public void testIgnored(Description description) {
        writer.append('I');
    }

    /*
     * Internal methods
     */

    private PrintStream getWriter() {
        return writer;
    }

    protected void printHeader(Result result) {
        getWriter().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        getWriter().println("<testsuites>");
        writer.append("<testsuite name=");
    }

    protected void printFailures(Result result) {
        List<Failure> failures = result.getFailures();
        if (failures.isEmpty()) {
            return;
        }
        if (failures.size() == 1) {
            getWriter().println("There was " + failures.size() + " failure:");
        } else {
            getWriter().println("There were " + failures.size() + " failures:");
        }
        int i = 1;
        for (Failure each : failures) {
            printFailure(each, "" + i++);
        }
    }

    protected void printFailure(Failure each, String prefix) {
        getWriter().println(prefix + ") " + each.getTestHeader());
        getWriter().print(each.getTrace());
    }

    protected void printFooter(Result result) {
        getWriter().println("</testsuite>");
        getWriter().println("</testsuites>");
    }

    /**
     * Returns the formatted string of the elapsed time. Duplicated from
     * BaseTestRunner. Fix it.
     */
    protected String elapsedTimeAsString(long runTime) {
        return NumberFormat.getInstance().format((double) runTime / 1000);
    }
}