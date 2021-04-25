package scanner.implementations.longmethod;

import model.ScanResult;

public class LongMethodScanResult implements ScanResult {

    String name = "Long Method";
    private int numberOfMethods;
    private int longMethodsDetected;

    public LongMethodScanResult(int numberOfMethods, int longMethodsDetected) {
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

    @Override
    public String getName() {
        return name;
    }

    public int getDetections() {
        return longMethodsDetected;
    }
}
