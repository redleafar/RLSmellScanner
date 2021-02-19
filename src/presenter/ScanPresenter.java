package presenter;

import implementations.largeclass.LargeClassScanner;
import implementations.longmethod.LongMethodScanner;
import model.ScanResult;
import scanner.MetricScanner;
import view.ScanView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ScanPresenter {
    public static void main(String[] args) {
        BufferedReader reader;
        ArrayList<MetricScanner> scannerArrayList = new ArrayList<>();
        ScanView scanView = new ScanView();
        ScanResult scanResult;

        LongMethodScanner longMethodScanner = new LongMethodScanner();
        LargeClassScanner largeClassScanner = new LargeClassScanner();

        scannerArrayList.add(longMethodScanner);
        scannerArrayList.add(largeClassScanner);

        try {
            for (MetricScanner scanner:scannerArrayList) {
                reader = new BufferedReader(new FileReader("src/primitiva.py"));
                scanResult = scanner.scan(reader);
                scanView.showResult(scanner, scanResult);
                scanView.addSeparator();
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
