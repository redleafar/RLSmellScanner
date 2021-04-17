package scanner.implementations.single;

import model.*;
import scanner.MetricScanner;
import scanner.implementations.longmethod.LongMethodScanResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Constants.DELIMITER;

public class MethodCodeLinesScanner implements MetricScanner {

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int totalLineCount = 1;
        int lineCount = 0;
        int spaceCount = 0;
        int numberOfMethods = 0;
        MethodInfo methodInfo = null;
        ArrayList<MethodInfo> methodInfoList = new ArrayList<MethodInfo>();

        String line = reader.readLine();

        while (line != null) {
            if (isMethodStart(line)) {
                if (methodInfo != null) {
                    methodInfoList.add(methodInfo);
                }
                methodInfo = new MethodInfo(getMethodName(line), totalLineCount, 0);
                spaceCount = 0;
                lineCount = 0;
                numberOfMethods++;
            } else if(numberOfMethods > 0 && isMethodContent(line)) {
                lineCount += spaceCount + 1;
                methodInfo.setLinesOfCode(lineCount);
                spaceCount = 0;
            } else {
                spaceCount++;
            }

            totalLineCount++;
            line = reader.readLine();
        }

        if (methodInfo != null) {
            methodInfoList.add(methodInfo);
        }

        return new MethodCodeLinesScanResult(numberOfMethods, methodInfoList);
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int methodsDetected = ((MethodCodeLinesScanResult) scanResult).getNumberOfMethods();

        System.out.println("Metric: Method Lines of Code \n");

        ArrayList<MethodInfo> methodInfoList = ((MethodCodeLinesScanResult) scanResult).getMethodInfoList();

        for (MethodInfo methodInfo: methodInfoList) {
            System.out.println("Name of method: " + methodInfo.getName());
            System.out.println("Starting line: " + methodInfo.getStartingLine());
            System.out.println("Lines of Code: " + methodInfo.getLinesOfCode() + "\n");
        }

        System.out.println("Total number of methods: " + methodsDetected + "\n");
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

    private String getMethodName(String line) {
        Matcher m = Pattern.compile("def (.*?)\\(").matcher(line);
        String name = "";

        while(m.find()) {
            name = m.group(1);
        }

        return name;
    }
}
