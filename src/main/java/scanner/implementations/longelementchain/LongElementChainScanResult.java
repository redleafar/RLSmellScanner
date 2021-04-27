package scanner.implementations.longelementchain;

import model.ScanResult;

public class LongElementChainScanResult implements ScanResult {

    String name = "Long Elements Chain";
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

    @Override
    public String getName() {
        return name;
    }

    public int getDetections() {
        return longElementsChainDetected;
    }
}
