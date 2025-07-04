package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static List<String> readNames(String path) throws Exception {
        List<String> testDataNames = new ArrayList<>();

        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        int numberOfRows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < numberOfRows; i++) { // начинаем с 1 — пропускаем заголовок
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    testDataNames.add(cell.getStringCellValue());
                }
            }
        }
        workbook.close();
        file.close();
        return testDataNames;
    }
}