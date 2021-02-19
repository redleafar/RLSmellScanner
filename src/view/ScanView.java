package view;

import model.ScanResult;
import scanner.MetricScanner;

public class ScanView {
    public void showResult(MetricScanner metricScanner, ScanResult scanResult) {
        metricScanner.showResult(scanResult);
    }

    public void addSeparator() {
        System.out.println();
    }
}
