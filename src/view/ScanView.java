package view;

import model.ScanResult;
import scanner.MetricScanner;

public class ScanView {
    public void showResult(String fileName, MetricScanner metricScanner, ScanResult scanResult) {
        System.out.println("Showing results for: " + fileName);
        metricScanner.showResult(scanResult);
    }

    public void addSeparator() {
        System.out.println();
    }
}
