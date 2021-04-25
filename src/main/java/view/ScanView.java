package view;

import model.ScanResult;
import scanner.MetricScanner;

import java.util.ArrayList;

public interface ScanView {
    void setup(ArrayList<MetricScanner> metricsList, int numberOfFiles, String projectFolder);
    void generateReport(String fileName, MetricScanner metricScanner, ScanResult scanResult);
    void saveReport(String fileName);
}
