package scanner.implementations.longmessagechain;

import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;

public class LongMessageChainScanner implements MetricScanner {

    public static int LongMessageChainLimit = 5;

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int longMessageChainsDetected = 0;
        String line = reader.readLine();

        while (line != null) {
            if (containsLongMethodChain(line)) {
                longMessageChainsDetected ++;
            }

            line = reader.readLine();
        }

        return new LongMessageChainResult(longMessageChainsDetected);
    }

    private boolean containsLongMethodChain(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");

        for (String word:words) {
            if (word.split("\\.").length > LongMessageChainLimit) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int longMessageChainResult = ((LongMessageChainResult)scanResult).getLongMessageChainsDetected();
        System.out.println("Long Message Chain Scanner result: ");
        System.out.println("Long Message Chains detected: " + longMessageChainResult);
    }
}
