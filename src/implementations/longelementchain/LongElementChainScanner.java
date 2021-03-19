package implementations.longelementchain;

import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongElementChainScanner implements MetricScanner {

    public static int LongElementChainLimit = 5;

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int longElementChain = 0;
        boolean countMultiLineParams = false;
        StringBuilder multiLineParamsStringBuilder = new StringBuilder();
        String multiLineElementsString = "";
        String line = reader.readLine();

        while (line != null) {
            if (countMultiLineParams && !isElementsListEndLine(line)) {
                multiLineParamsStringBuilder.append(line.trim());
                line = reader.readLine();
                continue;
            } else if (countMultiLineParams && isElementsListEndLine(line)) {
                multiLineParamsStringBuilder.append(line.trim());
                multiLineElementsString = multiLineParamsStringBuilder.toString();

                if (getElements(multiLineElementsString) > LongElementChainLimit) {
                    longElementChain++;
                }

                countMultiLineParams = false;
                multiLineParamsStringBuilder.setLength(0);
                line = reader.readLine();
                continue;
            }

            if (!isElementsListEndLine(line)) {
                countMultiLineParams = true;
                multiLineParamsStringBuilder.append(line.trim());
                line = reader.readLine();
                continue;
            }

            if (getElements(line) > LongElementChainLimit) {
                longElementChain++;
            }

            line = reader.readLine();
        }

        return new LongElementChainScanResult(longElementChain);
    }

    private boolean isElementsListEndLine(String line) {
        String newLine = line.trim();
        char lastChar = ' ';

        if (newLine.length() > 0) {
            lastChar = newLine.charAt(newLine.length() - 1);
        }

        return lastChar == ']';
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int longElementsListDetected = ((LongElementChainScanResult)scanResult).getlongElementsChainDetected();
        System.out.println("Long Elements Chain result: ");
        System.out.println("Long Elements Chain detected: " + longElementsListDetected);
    }

    private int getElements(String text) {
        Matcher m = Pattern.compile("\\[([^)]+)]").matcher(text);
        String parameters = "";
        String[] parametersArray;

        while(m.find()) {
            parameters = m.group(1);
        }

        parametersArray = parameters.split(",");
        return parametersArray.length;
    }
}
