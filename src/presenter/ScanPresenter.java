package presenter;

import implementations.largeclass.LargeClassScanner;
import implementations.longelementchain.LongElementChainScanner;
import implementations.longlambdafunction.LongLambdaFunctionScanner;
import implementations.longmessagechain.LongMessageChainScanner;
import implementations.longmethod.LongMethodScanner;
import implementations.longparamlist.LongParamListScanner;
import implementations.longscopechaining.LongScopeChainingScanner;
import implementations.longternary.LongTernaryOperatorScanner;
import model.ScanResult;
import scanner.MetricScanner;
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

        try (Stream<Path> walk = Files.walk(Paths.get("src/testfiles"))) {

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
        LargeClassScanner largeClassScanner = new LargeClassScanner();
        LongParamListScanner longParamListScanner = new LongParamListScanner();
        LongMessageChainScanner longMessageChainScanner = new LongMessageChainScanner();
        LongScopeChainingScanner longScopeChainingScanner = new LongScopeChainingScanner();
        LongTernaryOperatorScanner longTernaryOperatorScanner = new LongTernaryOperatorScanner();
        LongElementChainScanner longElementChainScanner = new LongElementChainScanner();
        LongLambdaFunctionScanner longLambdaFunctionScanner = new LongLambdaFunctionScanner();

        scannerArrayList.add(longMethodScanner);
        scannerArrayList.add(largeClassScanner);
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
