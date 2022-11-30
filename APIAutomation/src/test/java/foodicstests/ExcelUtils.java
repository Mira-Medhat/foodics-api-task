package foodicstests;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    String excelPath = "./data/users.xlsx";
    // public static sheetName ="users";


    public ExcelUtils(String excelPath, String sheetName) throws IOException {
        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }


    public static Object getCellData(int rowNum, int colNum) {

        DataFormatter formatter = new DataFormatter();
        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
        System.out.println("cellValue: " + value);
        return value;

    }


}
