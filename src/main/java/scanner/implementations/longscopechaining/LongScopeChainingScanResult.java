package scanner.implementations.longscopechaining;

import model.ScanResult;

public class LongScopeChainingScanResult implements ScanResult {

    String name = "Long Scope Chaining";
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

    @Override
    public String getName() {
        return name;
    }

    public int getDetections() {
        return longScopeChainingsDetected;
    }
}
