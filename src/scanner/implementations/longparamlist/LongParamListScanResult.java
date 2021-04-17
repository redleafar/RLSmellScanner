package scanner.implementations.longparamlist;

import model.ScanResult;

public class LongParamListScanResult implements ScanResult {

    private int longParamListDetected;

    public LongParamListScanResult(int longParamListDetected) {
        this.longParamListDetected = longParamListDetected;
    }

    public int getLongParamListDetected() {
        return longParamListDetected;
    }

    public void setLongParamListDetected(int longParamListDetected) {
        this.longParamListDetected = longParamListDetected;
    }
}
