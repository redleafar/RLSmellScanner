package view;

import model.ScanResult;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import scanner.MetricScanner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ScanViewExcelImpl implements ScanView {
    public static int FIRST_ROW = 5;
    public static int FIRST_COLUMN = 5;
    public static int ZOOM_LEVEL = 150;
    XSSFWorkbook workbook = new XSSFWorkbook();
    Sheet sheet;
    int rowNumber = FIRST_ROW;
    int columnNumber = FIRST_COLUMN;
    String currentFileName = "";
    int fileCodeSmellTotal = 0;
    int numberOfSmellsToMeasure = 0;
    int numberOfFiles = 0;
    int[] totalArray;

    @Override
    public void setup(ArrayList<MetricScanner> metricsList, int numberOfFiles, String projectFolder) {
        String[] projectNameArray = projectFolder.split("/");
        String shortProjectName = projectNameArray[projectNameArray.length - 1];
        sheet = workbook.createSheet(shortProjectName);
        sheet.setZoom(ZOOM_LEVEL);

        Row header = sheet.createRow(FIRST_ROW);
        Cell headerCell = header.createCell(FIRST_COLUMN);
        headerCell.setCellValue("Code Smells");
        headerCell.setCellStyle(getHeaderStyle());

        CellStyle style = workbook.createCellStyle();

        sheet.setColumnWidth(FIRST_COLUMN, 6000);

        numberOfSmellsToMeasure = metricsList.size();
        this.numberOfFiles = numberOfFiles;

        totalArray = new int[numberOfSmellsToMeasure];

        for (int i = 0;i < metricsList.size();i++) {
            Row row = sheet.createRow(FIRST_ROW + 1 + i);
            Cell cell = row.createCell(FIRST_COLUMN);
            cell.setCellValue(metricsList.get(i).getName());
            cell.setCellStyle(style);
        }

        Row row = sheet.createRow(FIRST_ROW + 1 + numberOfSmellsToMeasure);
        Cell cell = row.createCell(FIRST_COLUMN);
        cell.setCellValue("Total");
        cell.setCellStyle(style);
    }

    @Override
    public void generateReport(String fileName, MetricScanner metricScanner, ScanResult scanResult) {
        if (!fileName.equals(currentFileName)) {
            rowNumber = FIRST_ROW;
            columnNumber++;
            currentFileName = fileName;
            String[] fileNameArray = fileName.split("/");
            String shortFileName = fileNameArray[fileNameArray.length - 1];
            createHeaderCell(shortFileName);

            rowNumber++;
            createRegularCell(scanResult.getDetections());
            totalArray[rowNumber - FIRST_ROW] += scanResult.getDetections();
            fileCodeSmellTotal += scanResult.getDetections();
        } else {
            createRegularCell(scanResult.getDetections());
            totalArray[rowNumber - FIRST_ROW - 1] += scanResult.getDetections();
            fileCodeSmellTotal += scanResult.getDetections();

            if (rowNumber - FIRST_ROW == numberOfSmellsToMeasure) {
                addCodeSmellTotalRow();

                if (columnNumber - FIRST_COLUMN == numberOfFiles) {
                    addTotalColumn();
                }
            }
        }

        rowNumber++;
    }

    @Override
    public void saveReport(String fileName) {
        String[] fileNameArray = fileName.split("/");
        String shortFileName = fileNameArray[fileNameArray.length - 1];
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + shortFileName + ".xlsx";

        if (numberOfFiles > 0) {
            try {
                FileOutputStream outputStream = new FileOutputStream(fileLocation);
                workbook.write(outputStream);
                workbook.close();
            } catch(Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private void createHeaderCell(String title) {
        Row header = sheet.getRow(rowNumber);
        sheet.setColumnWidth(columnNumber, 6000);
        Cell headerCell = header.createCell(columnNumber);
        headerCell.setCellValue(title);
        headerCell.setCellStyle(getHeaderStyle());
    }

    private void createRegularCell(int value) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row = sheet.getRow(rowNumber);
        Cell cell = row.createCell(columnNumber);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void addCodeSmellTotalRow() {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        Row row = sheet.getRow(rowNumber + 1);
        Cell cell = row.createCell(columnNumber);
        sheet.setColumnWidth(columnNumber, 6000);
        cell.setCellValue(fileCodeSmellTotal);
        cell.setCellStyle(style);
        fileCodeSmellTotal = 0;
    }

    private void addTotalColumn() {
        rowNumber = FIRST_ROW;
        columnNumber++;
        createHeaderCell("Total");

        int total = 0;

        for (int i=0;i < numberOfSmellsToMeasure;i++) {
            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);
            Row row = sheet.getRow(FIRST_ROW + i + 1);
            Cell cell = row.createCell(columnNumber);
            sheet.setColumnWidth(columnNumber, 6000);
            cell.setCellValue(totalArray[i]);
            cell.setCellStyle(style);
            total += totalArray[i];
        }

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        Row row = sheet.getRow(FIRST_ROW + numberOfSmellsToMeasure + 1);
        Cell cell = row.createCell(columnNumber);
        sheet.setColumnWidth(columnNumber, 6000);
        cell.setCellValue(total);
        cell.setCellStyle(style);
    }

    private CellStyle getHeaderStyle() {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        return headerStyle;
    }
}
