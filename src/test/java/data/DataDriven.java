package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public ArrayList<String> getData(String testcaseName,String sheetName) throws IOException {

		ArrayList<String> al = new ArrayList<String>();

		//FileInputStream fis = new FileInputStream("C:\\Users\\creat\\Projects2021\\Document1.xlsx");
		
		FileInputStream fis = new FileInputStream("C:\\Users\\creat\\eclipse-workspace3\\ExcelDriven\\src\\test\\java\\data\\Document1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify TestCases column by scanning the entire first row
				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int col = 0;

				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						col = k;

					}
					k++;
				}
				System.out.println(col);
				// Once column is identified then scan entire testcase column to identify
				// purchase test case

				while (rows.hasNext()) {
					Row r = rows.next();

					if (r.getCell(col).getStringCellValue().equalsIgnoreCase(testcaseName)) {

						// After you grab purchase test case row pull all the data and feed into test
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {

							Cell c = cv.next();
							if (c.getCellTypeEnum() == CellType.STRING) {
								al.add(c.getStringCellValue());
							} else {
								al.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								//al.add(c.getNumericCellValue());
							}
						}

					}
				}
			}

		}
		return al;
	}

}
