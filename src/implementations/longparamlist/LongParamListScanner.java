package implementations.longparamlist;

import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;

public class LongParamListScanner implements MetricScanner {

    public static int LongParamListLimit = 7;

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int lineCount = 0;
        int spaceCount = 0;
        int numberOfMethods = 0;
        int longMethodsDetected = 0;
        String line = reader.readLine();

        while (line != null) {
            if (isMethodStart(line)) {
                if (lineCount > LongParamListLimit) {
                    longMethodsDetected++;
                }

                spaceCount = 0;
                lineCount = 0;
                numberOfMethods++;
            } else if(numberOfMethods > 0 && isMethodContent(line)) {
                lineCount += spaceCount + 1;
                spaceCount = 0;
            } else {
                spaceCount++;
            }

            line = reader.readLine();
        }

        if (lineCount > LongParamListLimit) {
            longMethodsDetected++;
        }

        return new LongParamListScanResult(numberOfMethods, longMethodsDetected);
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int methodsDetected = ((LongParamListScanResult)scanResult).getNumberOfMethods();
        int longMethodsDetected = ((LongParamListScanResult)scanResult).getLongMethodsDetected();
        System.out.println("Long Method Scanner result: ");
        System.out.println("Long methods detected: " + longMethodsDetected);
        System.out.println("Number of methods: " + methodsDetected);
    }

    private static boolean isMethodStart(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");
        return words[0].equals("def");
    }

    private static boolean isMethodContent(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");
        String firstElement = words[0];

        return words.length > 1 || firstElement.length() > 0;
    }
}
