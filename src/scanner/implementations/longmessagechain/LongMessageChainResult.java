package scanner.implementations.longmessagechain;

import model.ScanResult;

public class LongMessageChainResult implements ScanResult {

    private int longMessageChainsDetected;

    public LongMessageChainResult(int longMessageChainsDetected) {
        this.longMessageChainsDetected = longMessageChainsDetected;
    }

    public int getLongMessageChainsDetected() {
        return longMessageChainsDetected;
    }

    public void setLongMessageChainsDetected(int longMessageChainsDetected) {
        this.longMessageChainsDetected = longMessageChainsDetected;
    }
}
