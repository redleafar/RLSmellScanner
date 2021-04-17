package model;

import java.util.ArrayList;

public class MethodCodeLinesScanResult implements ScanResult {

    private int numberOfMethods;
    private ArrayList<MethodInfo> methodInfoList;

    public MethodCodeLinesScanResult(int numberOfMethods, ArrayList<MethodInfo> methodInfoList) {
        this.numberOfMethods = numberOfMethods;
        this.methodInfoList = methodInfoList;
    }

    public int getNumberOfMethods() {
        return numberOfMethods;
    }

    public void setNumberOfMethods(int numberOfMethods) {
        this.numberOfMethods = numberOfMethods;
    }

    public ArrayList<MethodInfo> getMethodInfoList() {
        return methodInfoList;
    }

    public void setMethodInfoList(ArrayList<MethodInfo> methodInfoList) {
        this.methodInfoList = methodInfoList;
    }
}
