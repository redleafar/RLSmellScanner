package implementations.longparamlist;

import model.ScanResult;

public class LongParamListScanResult implements ScanResult {

    private int numberOfMethods;
    private int longMethodsDetected;

    public LongParamListScanResult(int numberOfMethods, int longMethodsDetected) {
        this.numberOfMethods = numberOfMethods;
        this.longMethodsDetected = longMethodsDetected;
    }

    public int getNumberOfMethods() {
        return numberOfMethods;
    }

    public void setNumberOfMethods(int numberOfMethods) {
        this.numberOfMethods = numberOfMethods;
    }

    public int getLongMethodsDetected() {
        return longMethodsDetected;
    }

    public void setLongMethodsDetected(int longMethodsDetected) {
        this.longMethodsDetected = longMethodsDetected;
    }
}
