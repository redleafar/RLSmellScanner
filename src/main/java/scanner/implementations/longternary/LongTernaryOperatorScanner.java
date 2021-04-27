package scanner.implementations.longternary;

import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;

public class LongTernaryOperatorScanner implements MetricScanner {

    public static String NAME = "Long Ternary Operator";
    public static int LongTernaryOperatorLimit = 54;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int longTernaryOperatorsDetected = 0;
        String line = reader.readLine();

        while (line != null) {
            if (lineHasTernaryOperator(line)) {
                if (line.trim().length() > LongTernaryOperatorLimit) {
                    longTernaryOperatorsDetected++;
                }
            }

            line = reader.readLine();
        }

        return new LongTernaryOperatorResult(longTernaryOperatorsDetected);
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int longTernaryOperators = ((LongTernaryOperatorResult)scanResult).getLongTernaryOperatorsDetected();
        System.out.println("Long Ternary Operator Scanner result: ");
        System.out.println("Long Ternary Operators Scanner detected: " + longTernaryOperators);
    }

    private boolean lineHasTernaryOperator(String line) {
        return line.contains("if") && line.contains("else");
    }
}
