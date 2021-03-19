package implementations.longelementchain;

import model.ScanResult;

public class LongElementChainScanResult implements ScanResult {

    private int longElementsChainDetected;

    public LongElementChainScanResult(int longElementsChainDetected) {
        this.longElementsChainDetected = longElementsChainDetected;
    }

    public int getlongElementsChainDetected() {
        return longElementsChainDetected;
    }

    public void setlongElementsChainDetected(int longElementsChainDetected) {
        this.longElementsChainDetected = longElementsChainDetected;
    }
}
