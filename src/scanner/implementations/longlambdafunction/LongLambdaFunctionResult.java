package scanner.implementations.longlambdafunction;

import model.ScanResult;

public class LongLambdaFunctionResult implements ScanResult {

    private int longLambdaFunctionsDetected;

    public LongLambdaFunctionResult(int longLambdaFunctionsDetected) {
        this.longLambdaFunctionsDetected = longLambdaFunctionsDetected;
    }

    public int getlongLambdaFunctionsDetected() {
        return longLambdaFunctionsDetected;
    }

    public void setlongLambdaFunctionsDetected(int longLambdaFunctionsDetected) {
        this.longLambdaFunctionsDetected = longLambdaFunctionsDetected;
    }
}
