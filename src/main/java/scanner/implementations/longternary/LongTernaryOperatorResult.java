package scanner.implementations.longternary;

import model.ScanResult;

public class LongTernaryOperatorResult implements ScanResult {

    String name = "Long Ternary Operator";
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

    @Override
    public String getName() {
        return name;
    }

    public int getDetections() {
        return longTernaryOperatorsDetected;
    }
}
