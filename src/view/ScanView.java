package view;

import model.ScanResult;
import scanner.MetricScanner;

import static utils.Constants.DELIMITER;

public class ScanView {
    public void showResult(String fileName, MetricScanner metricScanner, ScanResult scanResult) {
        System.out.println(DELIMITER);
        System.out.println("Showing results for: " + fileName + "\n");
        metricScanner.showResult(scanResult);
    }

    public void addSeparator() {
        System.out.println();
    }
}
