package implementations.longternary;

import model.ScanResult;

public class LongTernaryOperatorResult implements ScanResult {

    private int longTernaryOperatorsDetected;

    public LongTernaryOperatorResult(int longTernaryOperatorsDetected) {
        this.longTernaryOperatorsDetected = longTernaryOperatorsDetected;
    }

    public int getLongTernaryOperatorsDetected() {
        return longTernaryOperatorsDetected;
    }

    public void setLongTernaryOperatorsDetected(int longTernaryOperatorsDetected) {
        this.longTernaryOperatorsDetected = longTernaryOperatorsDetected;
    }
}
