package implementations.largeclass;

import implementations.longmethod.LongMethodScanResult;
import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;

public class LargeClassScanner implements MetricScanner {

    public static int LargeClassLinesLimit = 30;

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int lineCount = 0;
        int spaceCount = 0;
        int numberOfClasses = 0;
        int largeClassesDetected = 0;
        String line = reader.readLine();

        while (line != null) {
            if (isClassStart(line)) {
                if (lineCount > LargeClassLinesLimit) {
                    largeClassesDetected++;
                }

                spaceCount = 0;
                lineCount = 0;
                numberOfClasses++;
            } else if(numberOfClasses > 0 && isClassContent(line)) {
                lineCount += spaceCount + 1;
                spaceCount = 0;
            } else {
                spaceCount++;
            }

            line = reader.readLine();
        }

        if (lineCount > LargeClassLinesLimit) {
            largeClassesDetected++;
        }

        return new LargeClassScanResult(numberOfClasses, largeClassesDetected);
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int classesDetected = ((LargeClassScanResult)scanResult).getNumberOfClasses();
        int largeClassesDetected = ((LargeClassScanResult)scanResult).getLargeClassesDetected();
        System.out.println("Large Class Scanner result: ");
        System.out.println("Large Classes detected: " + largeClassesDetected);
        System.out.println("Number of classes: " + classesDetected);
    }

    private static boolean isClassStart(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");
        return words[0].equals("class");
    }

    private static boolean isClassContent(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");
        String firstElement = words[0];

        return words.length > 1 || firstElement.length() > 0;
    }
}
