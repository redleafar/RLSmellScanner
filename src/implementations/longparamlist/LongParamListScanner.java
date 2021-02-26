package implementations.longparamlist;

import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongParamListScanner implements MetricScanner {

    public static int LongParamListLimit = 5;

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int lineCount = 0;
        int spaceCount = 0;
        int longParamsDetected = 0;
        String line = reader.readLine();

        while (line != null) {
            if (isMethodOrClassStart(line) && getParameters(line) > LongParamListLimit) {
                longParamsDetected++;
            }
            line = reader.readLine();
        }

        return new LongParamListScanResult(longParamsDetected);
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int longParamListDetected = ((LongParamListScanResult)scanResult).getLongParamListDetected();
        System.out.println("Long Parameter Scanner result: ");
        System.out.println("Long Parameter classes and methods detected: " + longParamListDetected);
    }

    private boolean isMethodOrClassStart(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");
        return words[0].equals("def") || words[0].equals("class");
    }

    private int getParameters(String text) {
        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(text);
        String parameters = "";
        String[] parametersArray;

        while(m.find()) {
            parameters = m.group(1);
        }

        parametersArray = parameters.split(",");
        return parametersArray.length;
    }
}
