package view;

import model.ScanResult;
import scanner.MetricScanner;

import java.util.ArrayList;

import static utils.Constants.DELIMITER;

public class ScanViewTerminalImpl implements ScanView {

    @Override
    public void setup(ArrayList<MetricScanner> metricsList, int numberOfFiles, String projectFolder) {

    }

    public void generateReport(String fileName, MetricScanner metricScanner, ScanResult scanResult) {
        System.out.println(DELIMITER);
        System.out.println("Showing results for: " + fileName + "\n");
        metricScanner.showResult(scanResult);
        System.out.println();
    }

    @Override
    public void saveReport(String fileName) {

    }
}
