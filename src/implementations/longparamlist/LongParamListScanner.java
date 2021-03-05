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
        int longParamsDetected = 0;
        boolean countMultiLineParams = false;
        StringBuilder multiLineParamsStringBuilder = new StringBuilder();
        String multiLineParamsString = "";
        String line = reader.readLine();

        while (line != null) {
            if (isNotMethodOrClassStart(line) && !countMultiLineParams) {
                line = reader.readLine();
                continue;
            } else if (isNotMethodOrClassStart(line) && countMultiLineParams && !isParamsListEndLine(line)) {
                multiLineParamsStringBuilder.append(line.trim());
                line = reader.readLine();
                continue;
            } else if (isNotMethodOrClassStart(line) && countMultiLineParams && isParamsListEndLine(line)) {
                multiLineParamsStringBuilder.append(line.trim());
                multiLineParamsString = multiLineParamsStringBuilder.toString();

                if (getParameters(multiLineParamsString) > LongParamListLimit) {
                    longParamsDetected++;
                }

                countMultiLineParams = false;
                multiLineParamsStringBuilder.setLength(0);
                line = reader.readLine();
                continue;
            }

            if (!isParamsListEndLine(line)) {
                countMultiLineParams = true;
                multiLineParamsStringBuilder.append(line.trim());
                line = reader.readLine();
                continue;
            }

            if (getParameters(line) > LongParamListLimit) {
                longParamsDetected++;
            }

            line = reader.readLine();
        }

        return new LongParamListScanResult(longParamsDetected);
    }

    private boolean isParamsListEndLine(String line) {
        String newLine = line.trim();
        char lastChar = ' ';

        if (newLine.length() > 0) {
            lastChar = newLine.charAt(newLine.length() - 1);
        }

        return lastChar == ':';
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int longParamListDetected = ((LongParamListScanResult)scanResult).getLongParamListDetected();
        System.out.println("Long Parameter Scanner result: ");
        System.out.println("Long Parameter classes and methods detected: " + longParamListDetected);
    }

    private boolean isNotMethodOrClassStart(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");
        return !words[0].equals("def") && !words[0].equals("class");
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
