package scanner.implementations.longelementchain;

import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;

public class LongElementChainScanner implements MetricScanner {

    public static String NAME = "Long Element Chain";
    public static int LongElementChainLimit = 3;
    public static String SINGLE_LINE = "SINGLE LINE";
    public static String MULTI_LINE = "MULTI LINE";
    public static String NOT_A_CONTAINER = "NOT A CONTAINER";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int longElementChain = 0;
        boolean countMultiLineParams = false;
        StringBuilder multiLineParamsStringBuilder = new StringBuilder();
        String multiLineElementsString = "";
        String line = reader.readLine();

        long totalMultiLineOpenElements = 0;
        long totalMultiLineCloseElements = 0;

        while (line != null) {
            totalMultiLineOpenElements += getMultiLineOpenElements(line);
            totalMultiLineCloseElements += getMultiLineCloseElements(line);

            if (countMultiLineParams && (totalMultiLineOpenElements != totalMultiLineCloseElements) ) {
                multiLineParamsStringBuilder.append(line.trim());
                line = reader.readLine();
                continue;
            } else if (countMultiLineParams && (totalMultiLineOpenElements == totalMultiLineCloseElements)) {
                multiLineParamsStringBuilder.append(line.trim());
                multiLineElementsString = multiLineParamsStringBuilder.toString();

                if (getNestedContainerDepth(multiLineElementsString) > LongElementChainLimit) {
                    longElementChain++;
                }

                countMultiLineParams = false;
                multiLineParamsStringBuilder.setLength(0);
                totalMultiLineOpenElements = 0;
                totalMultiLineCloseElements = 0;
                line = reader.readLine();
                continue;
            }

            if ((isSingleOrMultiLineContainer(line).equals(MULTI_LINE)) && !countMultiLineParams) {
                countMultiLineParams = true;
                multiLineParamsStringBuilder.append(line.trim());
                line = reader.readLine();
                continue;
            } else if (isSingleOrMultiLineContainer(line).equals(SINGLE_LINE)) {
                if (getNestedContainerDepth(line) > LongElementChainLimit) {
                    longElementChain++;
                }
            }

            line = reader.readLine();
        }

        return new LongElementChainScanResult(longElementChain);
    }

    private boolean isContainer(String line) {
        long numOfOpenBraces = line.chars().filter(ch -> ch == '[').count();
        long numOfOpenParenthesis = line.chars().filter(ch -> ch == '(').count();

        return (numOfOpenBraces > 0) || (numOfOpenParenthesis > 0);
    }

    private String isSingleOrMultiLineContainer(String line) {
        if (!isContainer(line)) {
            return NOT_A_CONTAINER;
        } else if (getMultiLineOpenElements(line) != getMultiLineCloseElements(line)) {
            return MULTI_LINE;
        } else {
            return SINGLE_LINE;
        }
    }

    private long getMultiLineOpenElements(String line) {
        long numOfOpenBraces = line.chars().filter(ch -> ch == '[').count();
        long numOfOpenParenthesis = line.chars().filter(ch -> ch == '(').count();

        return numOfOpenBraces + numOfOpenParenthesis;
    }

    private long getMultiLineCloseElements(String line) {
        long numOfCloseBraces = line.chars().filter(ch -> ch == ']').count();
        long numOfCloseParenthesis = line.chars().filter(ch -> ch == ')').count();

        return numOfCloseBraces + numOfCloseParenthesis;
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int longElementsListDetected = ((LongElementChainScanResult)scanResult).getlongElementsChainDetected();
        System.out.println("Long Elements Chain result: ");
        System.out.println("Long Elements Chain detected: " + longElementsListDetected);
    }

    private int getNestedContainerDepth(String text) {
        char[] chars = text.trim().toCharArray();
        int tempDepth = 0;
        int maxDepth = 0;

        for (char ch: chars) {
            if (isStartOfContainer(ch)) {
                tempDepth ++;

                if (tempDepth > maxDepth) {
                    maxDepth = tempDepth;
                }
            } else if (isEndOfContainer(ch)) {
                tempDepth --;
            }
        }

        return maxDepth;
    }

    private boolean isStartOfContainer(char ch) {
        return (ch == '(') || (ch == '[');
    }

    private boolean isEndOfContainer(char ch) {
        return (ch == ')') || (ch == ']');
    }
}
