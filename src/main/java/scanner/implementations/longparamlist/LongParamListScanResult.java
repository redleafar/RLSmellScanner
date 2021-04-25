package scanner.implementations.longparamlist;

import model.ScanResult;

public class LongParamListScanResult implements ScanResult {

    String name = "Long Parameters List";
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDetections() {
        return longParamListDetected;
    }
}
