package implementations.largeclass;

import model.ScanResult;

public class LargeClassScanResult implements ScanResult {

    private int numberOfClasses;
    private int largeClassesDetected;

    public LargeClassScanResult(int numberOfClasses, int longClassesDetected) {
        this.numberOfClasses = numberOfClasses;
        this.largeClassesDetected = longClassesDetected;
    }


    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public int getLargeClassesDetected() {
        return largeClassesDetected;
    }

    public void setLargeClassesDetected(int largeClassesDetected) {
        this.largeClassesDetected = largeClassesDetected;
    }
}
