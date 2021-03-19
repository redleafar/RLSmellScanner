package implementations.longlambdafunction;

import model.ScanResult;
import scanner.MetricScanner;

import java.io.BufferedReader;
import java.io.IOException;

public class LongLambdaFunctionScanner implements MetricScanner {

    public static int LongLambdaFunctionsLimit = 80;

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int longLambdaFunctionsDetected = 0;
        String line = reader.readLine();

        while (line != null) {
            if (lineHasLambdaFunction(line)) {
                if (line.trim().length() > LongLambdaFunctionsLimit) {
                    longLambdaFunctionsDetected++;
                }
            }

            line = reader.readLine();
        }

        return new LongLambdaFunctionResult(longLambdaFunctionsDetected);
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int longTernaryOperators = ((LongLambdaFunctionResult)scanResult).getlongLambdaFunctionsDetected();
        System.out.println("Long Lambda Function Scanner result: ");
        System.out.println("Long Lambda Function Scanner detected: " + longTernaryOperators);
    }

    private boolean lineHasLambdaFunction(String line) {
        return line.contains("lambda") && line.contains(":");
    }
}
