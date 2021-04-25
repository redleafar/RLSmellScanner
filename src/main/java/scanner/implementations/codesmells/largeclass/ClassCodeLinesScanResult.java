package scanner.implementations.codesmells.largeclass;

import model.ScanResult;

/**
 * Result for the Class Lines Of Code
 */
public class ClassCodeLinesScanResult implements ScanResult {

    String name = "Large class";
    private int numberOfClasses;
    private int largeClassesDetected;

    public ClassCodeLinesScanResult(int numberOfClasses, int longClassesDetected) {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDetections() {
        return largeClassesDetected;
    }
}
