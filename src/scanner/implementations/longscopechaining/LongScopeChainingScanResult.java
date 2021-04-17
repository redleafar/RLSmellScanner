package scanner.implementations.longscopechaining;

import model.ScanResult;

public class LongScopeChainingScanResult implements ScanResult {

    private int longScopeChainingsDetected;

    public LongScopeChainingScanResult(int longScopeChainingsDetected) {
        this.longScopeChainingsDetected = longScopeChainingsDetected;
    }

    public int getLongScopeChainingsDetected() {
        return longScopeChainingsDetected;
    }

    public void setLongScopeChainingsDetected(int longScopeChainingsDetected) {
        this.longScopeChainingsDetected = longScopeChainingsDetected;
    }
}
