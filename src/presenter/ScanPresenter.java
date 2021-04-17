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
    public static void main(String[] args) {

        try (Stream<Path> walk = Files.walk(Paths.get("/Users/mac/Desktop/Proyecto de grado/RepositoriesPython/Arnold-master"))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            result.forEach(ScanPresenter::scanFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanFile(String fileName) {
        BufferedReader reader;
        ArrayList<MetricScanner> scannerArrayList = new ArrayList<>();
        ScanView scanView = new ScanView();
        ScanResult scanResult;

        LongMethodScanner longMethodScanner = new LongMethodScanner();
        ClassCodeLinesScanner classCodeLinesScanner = new ClassCodeLinesScanner();
        MethodCodeLinesScanner methodCodeLinesScanner = new MethodCodeLinesScanner();
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

        try {
            for (MetricScanner scanner:scannerArrayList) {
                reader = new BufferedReader(new FileReader(fileName));

                if (getFileExtension(fileName).equals("py")) {
                    scanResult = scanner.scan(reader);
                    scanView.showResult(fileName, scanner, scanResult);
                    scanView.addSeparator();
                }

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
