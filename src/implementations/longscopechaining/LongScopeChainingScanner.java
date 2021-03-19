package implementations.longscopechaining;

import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongScopeChainingScanner implements MetricScanner {

    public static int LongScopeChainingLimit = 3;

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int longScopeChainingsDetected = 0;
        int previousBeginOfLineSpaces = 0;
        int chainingCount = 0;
        String line = reader.readLine();

        while (line != null) {
            if (getBeginOfLineSpaces(line) > previousBeginOfLineSpaces) {
                chainingCount++;
            } else if (getBeginOfLineSpaces(line) < previousBeginOfLineSpaces) {
                if (chainingCount > LongScopeChainingLimit) {
                    longScopeChainingsDetected++;
                }
                chainingCount = 0;
            }
            previousBeginOfLineSpaces = getBeginOfLineSpaces(line);
            line = reader.readLine();
        }

        return new LongScopeChainingScanResult(longScopeChainingsDetected);
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int longScopeChainingsDetected = ((LongScopeChainingScanResult)scanResult).getLongScopeChainingsDetected();
        System.out.println("Long Scope Chaining Scanner result: ");
        System.out.println("Long Scope Chainings detected: " + longScopeChainingsDetected);
    }

    private int getBeginOfLineSpaces(String line) {
        if (line.trim().isEmpty()) {
            return line.length();
        } else {
            return line.indexOf(line.trim());
        }
    }
}
