package scanner.implementations.single;

import model.ClassInfo;
import model.ScanResult;
import scanner.MetricScanner;
import model.ClassCodeLinesScanResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassCodeLinesScanner implements MetricScanner {

    @Override
    public ScanResult scan(BufferedReader reader) throws IOException {
        int totalLineCount = 1;
        int lineCount = 0;
        int spaceCount = 0;
        int numberOfClasses = 0;
        ClassInfo classInfo = null;
        ArrayList<ClassInfo> classInfoList = new ArrayList<ClassInfo>();

        String line = reader.readLine();

        while (line != null) {
            if (isClassStart(line)) {
                if (classInfo != null) {
                    classInfoList.add(classInfo);
                }
                classInfo = new ClassInfo(getClassName(line), totalLineCount, 0);
                spaceCount = 0;
                lineCount = 0;
                numberOfClasses++;
            } else if(numberOfClasses > 0 && isClassContent(line)) {
                lineCount += spaceCount + 1;
                classInfo.setLinesOfCode(lineCount);
                spaceCount = 0;
            } else {
                spaceCount++;
            }

            totalLineCount++;
            line = reader.readLine();
        }

        if (classInfo != null) {
            classInfoList.add(classInfo);
        }

        return new ClassCodeLinesScanResult(numberOfClasses, classInfoList);
    }

    @Override
    public void showResult(ScanResult scanResult) {
        int classesDetected = ((ClassCodeLinesScanResult)scanResult).getNumberOfClasses();

        System.out.println("Metric: Class Lines of Code \n");

        ArrayList<ClassInfo> classInfoList = ((ClassCodeLinesScanResult) scanResult).getClassInfoList();

        for (ClassInfo classInfo: classInfoList) {
            System.out.println("Name of class: " + classInfo.getName());
            System.out.println("Starting line: " + classInfo.getStartingLine());
            System.out.println("Lines of Code: " + classInfo.getLinesOfCode() + "\n");
        }

        System.out.println("Total number of classes: " + classesDetected + "\n");
    }

    private static boolean isClassStart(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");
        return words[0].equals("class");
    }

    private static boolean isClassContent(String line) {
        String newLine = line.trim();
        String[] words = newLine.split(" ");
        String firstElement = words[0];

        return words.length > 1 || firstElement.length() > 0;
    }

    private String getClassName(String line) {
        Matcher m = Pattern.compile("class (.*?):").matcher(line);
        String name = "";

        while(m.find()) {
            name = m.group(1);
        }

        return name;
    }
}
