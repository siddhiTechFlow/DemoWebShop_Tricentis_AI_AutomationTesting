package com.automation.listeners;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * Prints a simple pass/fail summary in the terminal because Maven's default
 * summary shows "Tests run" but does not always show "Passed" explicitly.
 */
public class TerminalSummaryListener implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        int passed = 0;
        int failed = 0;
        int skipped = 0;

        for (ISuite suite : suites) {
            for (ISuiteResult result : suite.getResults().values()) {
                passed += result.getTestContext().getPassedTests().size();
                failed += result.getTestContext().getFailedTests().size();
                skipped += result.getTestContext().getSkippedTests().size();
            }
        }

        int total = passed + failed + skipped;
        System.out.println();
        System.out.println("============================================================");
        System.out.println("Default suite");
        System.out.println("Total tests run: " + total + ", Passes: " + passed
                + ", Failures: " + failed + ", Skips: " + skipped);
        System.out.println("============================================================");
        System.out.println();
        System.out.println("============================================================");
        System.out.println("TEST EXECUTION SUMMARY");
        System.out.println("TOTAL   : " + total);
        System.out.println("PASSED  : " + passed);
        System.out.println("FAILED  : " + failed);
        System.out.println("SKIPPED : " + skipped);
        System.out.println("============================================================");
        System.out.println();
    }
}
