package model;

import java.util.ArrayList;

public class ClassCodeLinesScanResult implements ScanResult {

    private int numberOfClasses;
    private ArrayList<ClassInfo> classInfoList;

    public ClassCodeLinesScanResult(int numberOfClasses, ArrayList<ClassInfo> classInfoList) {
        this.numberOfClasses = numberOfClasses;
        this.classInfoList = classInfoList;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public ArrayList<ClassInfo> getClassInfoList() {
        return classInfoList;
    }

    public void setClassInfoList(ArrayList<ClassInfo> classInfoList) {
        this.classInfoList = classInfoList;
    }
}
