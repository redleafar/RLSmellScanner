package scanner;

import model.ScanResult;

import java.io.BufferedReader;
import java.io.IOException;

public interface MetricScanner {
    String getName();
    ScanResult scan(BufferedReader reader) throws IOException;
    void showResult(ScanResult scanResult);
}
