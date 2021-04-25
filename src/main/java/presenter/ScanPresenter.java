package presenter;

import scanner.implementations.codesmells.largeclass.ClassCodeLinesScanner;
import scanner.implementations.longelementchain.LongElementChainScanner;
import scanner.implementations.longlambdafunction.LongLambdaFunctionScanner;
import scanner.implementations.longmessagechain.LongMessageChainScanner;
import scanner.implementations.longmethod.LongMethodScanner;
import scanner.implementations.longparamlist.LongParamListScanner;
import scanner.implementations.longscopechaining.LongScopeChainingScanner;
import scanner.implementations.longternary.LongTernaryOperatorScanner;
import model.ScanResult;
import scanner.MetricScanner;
import scanner.implementations.single.MethodCodeLinesScanner;
import view.ScanView;
import view.ScanViewExcelImpl;
import view.ScanViewTerminalImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScanPresenter {

    public static ScanView scanView = new ScanViewExcelImpl();
    public static ArrayList<MetricScanner> scannerArrayList = new ArrayList<>();
    public static void main(String[] args) {
        try (Stream<Path> walk = Files.walk(Paths.get("/Users/mac/Desktop/Proyecto de grado/RepositoriesPython/"))) {

            List<String> projects = walk.filter(Files::isDirectory)
                    .map(Path::toString).collect(Collectors.toList());

            projects.forEach(ScanPresenter::scanProject);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void scanProject(String projectFolder) {
        try (Stream<Path> walk = Files.walk(Paths.get(projectFolder))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            LongMethodScanner longMethodScanner = new LongMethodScanner();
            ClassCodeLinesScanner classCodeLinesScanner = new ClassCodeLinesScanner();
            LongParamListScanner longParamListScanner = new LongParamListScanner();
            LongMessageChainScanner longMessageChainScanner = new LongMessageChainScanner();
            LongScopeChainingScanner longScopeChainingScanner = new LongScopeChainingScanner();
            LongTernaryOperatorScanner longTernaryOperatorScanner = new LongTernaryOperatorScanner();
            LongElementChainScanner longElementChainScanner = new LongElementChainScanner();
            LongLambdaFunctionScanner longLambdaFunctionScanner = new LongLambdaFunctionScanner();

            scannerArrayList.add(longMethodScanner);
            scannerArrayList.add(classCodeLinesScanner);
            scannerArrayList.add(longParamListScanner);
            scannerArrayList.add(longMessageChainScanner);
            scannerArrayList.add(longScopeChainingScanner);
            scannerArrayList.add(longTernaryOperatorScanner);
            scannerArrayList.add(longElementChainScanner);
            scannerArrayList.add(longLambdaFunctionScanner);

            List<String> pythonFiles = getPythonFiles(result);
            scanView.setup(scannerArrayList, pythonFiles.size(), projectFolder);
            pythonFiles.forEach(ScanPresenter::scanFile);
            scanView.saveReport(projectFolder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getPythonFiles(List<String> files) {
        List<String> pythonFiles = new ArrayList<>();

        for (String file:files) {
            if (getFileExtension(file).equals("py")) {
                pythonFiles.add(file);
            }
        }

        return pythonFiles;
    }

    private static void scanFile(String fileName) {
        BufferedReader reader;
        ScanResult scanResult;

        try {
            for (MetricScanner scanner:scannerArrayList) {
                reader = new BufferedReader(new FileReader(fileName));
                scanResult = scanner.scan(reader);
                scanView.generateReport(fileName, scanner, scanResult);
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }

        return extension;
    }
}
